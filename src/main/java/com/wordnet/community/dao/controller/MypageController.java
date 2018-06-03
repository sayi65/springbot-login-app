package com.wordnet.community.dao.controller;

import com.wordnet.community.dao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
public class MypageController {

    private static final String PAGE = "/mypage";
    private static final String HTML = "mypage";

    @GetMapping(MypageController.PAGE)
    public String mypage(Model model,
                         Principal principal,
                         Authentication authentication) {
        User user= (User) authentication.getPrincipal();

        model.addAttribute("page", HTML);
        return MypageController.HTML;
    }
}
