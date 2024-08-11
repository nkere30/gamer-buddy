package com.kere.lil.sbet.security.capstoneproject.controller;

import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(@RequestParam String username, Model model) {
        logger.info("Accessing profile page for user: {}", username);
        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "profile";
        } else {
            logger.warn("Profile page access denied. No user found with username: {}", username);
            return "redirect:/login";
        }
    }
}
