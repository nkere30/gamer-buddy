package com.kere.lil.sbet.security.capstoneproject.controller;

import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        logger.info("Accessing login form.");
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        logger.info("Attempting login with username/email: {} and password: {}", user.getUsername(), user.getPassword());
        Optional<User> userOpt = userService.authenticateUser(user.getUsername(), user.getPassword());

        if (userOpt.isPresent()) {
            logger.info("Login successful for user: {}", userOpt.get().getUsername());
            return "redirect:/profile?username=" + userOpt.get().getUsername();
        } else {
            logger.warn("Login failed for username/email: {}", user.getUsername());
            model.addAttribute("error", "Invalid username/email and/or password");
            return "login";
        }
    }
}
