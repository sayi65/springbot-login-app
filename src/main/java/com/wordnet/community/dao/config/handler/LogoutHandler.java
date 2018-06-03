package com.wordnet.community.dao.config.handler;

import com.wordnet.community.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 003418
 */
@Component
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        User user= (User) authentication.getPrincipal();
        redisTemplate.delete(user.getUsername());
    }
}
