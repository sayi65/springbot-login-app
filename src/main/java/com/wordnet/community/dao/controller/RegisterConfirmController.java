package com.wordnet.community.dao.controller;

import com.wordnet.community.dao.entity.Profile;
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
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RegisterConfirmController {

    private static final String PAGE = "/registerConfirm";
    private static final String HTML = "registerConfirm";

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RedisTemplate<String, User> template;



    @GetMapping(RegisterConfirmController.PAGE)
    public String registerConfirm(Model model,
                                  Authentication authentication,
                                  Principal principal) {

//        if (principal != null){
//           return "redirect:/mypage";
//        }

        model.addAttribute("userRegister", new Profile());

        return RegisterConfirmController.HTML;
    }

    @PostMapping(RegisterConfirmController.PAGE)
    public String registerConfirmPost(
                                        @Valid Profile profile,
                                      Model model,
                                      Authentication authentication,
                                      HttpSession session,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RegisterConfirmController.HTML;
        }

        try {
            User user = (User) authentication.getPrincipal();

                user.setNickname(profile.getNickname());
                user.setSex(profile.getSex());
                user.setAvatar(profile.getAvatar().getOriginalFilename());
                user.setAge(profile.getAge());
                user.setIntroduction(profile.getIntroduction());
                userService.updateData(user);
                securityService.updateAuthentication(authentication);

                template.opsForValue().set(user.getUsername(), user);


        } catch (DataIntegrityViolationException e) {
            return RegisterConfirmController.HTML;
        }


        return "redirect:/mypage";
    }
}
