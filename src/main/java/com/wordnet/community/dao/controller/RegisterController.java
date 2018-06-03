package com.wordnet.community.dao.controller;


import com.wordnet.community.dao.entity.User;
import com.wordnet.community.dao.service.SecurityService;
import com.wordnet.community.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.concurrent.TimeUnit;

@Controller
@SessionAttributes(value = "User")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RedisTemplate<String, User> template;

    private static final String PAGE = "/register";
    private static final String HTML = "register";

    @GetMapping(RegisterController.PAGE)
    public String register(Model model, Principal principal){

        if (principal != null){
            return "redirect:/mypage";
        }
        model.addAttribute("user", new User());
        return RegisterController.HTML;
    }

    @PostMapping(RegisterController.PAGE)
    public String registerPost(Model model,
                               @Valid User user,
                               @Valid String confirmPassword,
                               Authentication authentication,
                               BindingResult bindingResult
                               ){
        if(bindingResult.hasErrors()){
            return RegisterController.HTML;
        }

        try {
                User existing = userService.findByUsername(user.getUsername());
            if(existing != null){
                bindingResult.rejectValue("username", null, "이미 가입된 아이디 입니다.");
            }

            userService.registerUser(user);
        }catch (DataIntegrityViolationException e){
           return RegisterController.HTML;
        }

        try {
            securityService.autologin(user.getUsername(),user.getPassword());

            if (template.opsForValue().get(user.getUsername()) == null){
                template.opsForValue().set(user.getUsername(), user, 360, TimeUnit.SECONDS);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/registerConfirm";
    }
}
