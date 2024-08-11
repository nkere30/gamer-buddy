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

@Controller
public class SignupController {

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        logger.info("Attempting to register user: {}", user.getUsername());
        String errorMessages = userService.getRegistrationErrors(user);
        if (!errorMessages.isEmpty()) {
            logger.warn("Registration failed for user: {} with errors: {}", user.getUsername(), errorMessages);
            model.addAttribute("error", errorMessages);
            return "signup";
        }

        userService.registerUser(user);
        logger.info("User {} registered successfully", user.getUsername());
        return "redirect:/profile?username=" + user.getUsername();
    }
}
