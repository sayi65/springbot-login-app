package com.wordnet.community.dao.controller;


import com.wordnet.community.dao.entity.User;
import com.wordnet.community.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

//    @Autowired
//    UserService userService;
//
//    @RequestMapping("/")
//    public String top(Model model){
//        List<User> userList = userService.findAll();
//
//        model.addAttribute("users", userList);
//        return "";
//    }
}
