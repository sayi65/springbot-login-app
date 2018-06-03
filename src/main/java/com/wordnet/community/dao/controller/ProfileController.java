package com.wordnet.community.dao.controller;

import com.wordnet.community.dao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author 003418
 */

@Controller
@Slf4j
public class ProfileController {

    private static final String PAGE = "/profile";
    private static final String HTML = "profile";

    @GetMapping(ProfileController.PAGE)
    public String getProfile(Model model,
                         Principal principal,
                         Authentication authentication) {
        User user= (User) authentication.getPrincipal();

        model.addAttribute("page", HTML);
        return ProfileController.HTML;
    }
}
