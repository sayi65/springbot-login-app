package com.wordnet.community.dao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class GalleryController {
    private static final String PAGE = "/gallery";
    private static final String HTML = "gallery";

    @GetMapping(GalleryController.PAGE)
    public String mypage(Model model,
                         Authentication authentication,
                         HttpSession session) {

        model.addAttribute("page", HTML);
        return GalleryController.HTML;
    }
}
