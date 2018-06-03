package com.wordnet.community.dao.service;


import com.wordnet.community.dao.Utility.Utility;
import com.wordnet.community.dao.entity.Profile;
import com.wordnet.community.dao.entity.User;
import com.wordnet.community.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("ユーザーIDが未入力です");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーIDが不正です。");
        }

        return (UserDetails) user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User registerUser (User user)  {

        if (loginIdExist(user.getUsername())){

        } else {
            user.setId(UUID.randomUUID());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreated(Utility.getNowTimestamp());
            user.setModified(Utility.getNowTimestamp());
        }
        return userRepository.save(user);
    }

    @Transactional
    public User updateData(User user){
        return userRepository.saveAndFlush(user);
    }

    private boolean loginIdExist(String loginId)
    {
        User user = userRepository.findByUsername(loginId);
        if (user != null){
            return true;
        } else {
            return false;
        }
    }
}
