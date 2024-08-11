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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class GameBuddyController {

    private static final Logger logger = LoggerFactory.getLogger(GameBuddyController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

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
            model.addAttribute("error", "Invalid username/email or password");
            return "login";
        }
    }


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
