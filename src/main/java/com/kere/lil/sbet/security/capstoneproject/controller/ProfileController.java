package com.kere.lil.sbet.security.capstoneproject.controller;

import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            return "redirect:/login";
        }

        String username = auth.getName(); // Get the username of the currently authenticated user

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "profile";
        } else {
            // Return an error page or handle the case gracefully
            model.addAttribute("error", "User not found.");
            return "error";  // Assuming you have an error.html template
        }
    }
}
