package com.wordnet.community.dao.config;

import com.wordnet.community.dao.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author 003418
 */
@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 43200)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 360)
public class HttpSessionConfig {


    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        return redisTemplate;
    }

    @Bean
    public static ConfigureRedisAction configureRedisAction(){
        return ConfigureRedisAction.NO_OP;
    }
}
