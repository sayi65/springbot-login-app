package com.wordnet.community.dao.config;

import com.wordnet.community.dao.config.handler.LogoutHandler;
import com.wordnet.community.dao.config.handler.MyAuthenticationFailureHandler;
import com.wordnet.community.dao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private LogoutHandler logoutHandler;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity.csrf()
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        httpSecurity.authorizeRequests()
                // アクセス権限の設定
                // staticディレクトリにある、'/css/','fonts','/js/'は制限なし
                .antMatchers("/css/**", "/fonts/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/", "/login", "/register").permitAll()
                // '/admin/'で始まるURLには、'ADMIN'ロールのみアクセス可
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 他は制限なし
                .anyRequest().authenticated()
                .and()
                // ログイン処理の設定
                .formLogin()
                // ログイン処理のURL
                .loginPage("/login")
                .defaultSuccessUrl("/mypage")
//                    .failureUrl("/login-error")
//                    .failureHandler(new MyAuthenticationFailureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                // ログアウト処理の設定
                .logout()
                // ログアウト処理のURL
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // ログアウト成功時の遷移先URL
                .logoutSuccessUrl("/login")
                //ログアウトのsession情報削除
                .addLogoutHandler(logoutHandler)
                // ログアウト時に削除するクッキー名
                .deleteCookies("JSESSIONID", "SESSION")
                // ログアウト時のセッション破棄を有効化
                .invalidateHttpSession(true)
                .permitAll()
        ;
    }

    @Configuration
    static class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean //パスワードの暗号化方式を宣言（平文でDBにパスワードを保存しないこと！）
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

    }
}
