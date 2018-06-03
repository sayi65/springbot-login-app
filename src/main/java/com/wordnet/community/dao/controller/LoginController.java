package com.wordnet.community.dao.controller;


import com.wordnet.community.dao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@Slf4j
public class LoginController {



    private static final String PAGE = "/login";
    private static final String HTML = "login";

    @RequestMapping(LoginController.PAGE)
    public String login(
                        Principal principal,
                        Exception exception,
                        Model model
                       ) {

        if (principal != null){
            return "redirect:/mypage";
        }

        if(exception != null){
            model.addAttribute("message", exception.getMessage());
        }

        return LoginController.HTML;
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return LoginController.HTML;
    }

    @RequestMapping("/logout")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String logout(
                         Authentication authentication) {
        User user= (User) authentication.getPrincipal();

        return "logout OK";
    }
}
