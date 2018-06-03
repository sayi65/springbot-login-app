package com.wordnet.community.dao.controller;

import com.wordnet.community.dao.entity.User;
import com.wordnet.community.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class TopController {


    @RequestMapping("/")
    public String top(Model model, Principal principal){

        if (principal != null){
            return "redirect:/mypage";
        }
//        return "registerConfirm";
        return "top";
    }
}
