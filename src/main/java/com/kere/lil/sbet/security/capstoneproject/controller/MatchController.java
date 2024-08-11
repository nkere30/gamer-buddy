package com.kere.lil.sbet.security.capstoneproject.controller;

import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.MatchService;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @GetMapping("/findBuddy")
    public String findBuddyPage(Model model) {
        User currentUser = userService.getCurrentUser();
        Optional<User> randomUserOpt = matchService.getRandomUserForMatching(currentUser);

        if (randomUserOpt.isPresent()) {
            model.addAttribute("matchedUser", randomUserOpt.get());
            return "findBuddy";  // Return the correct view name
        } else {
            model.addAttribute("message", "No available users for matching.");
            return "noMatches";
        }
    }

    @PostMapping("/findBuddy/match")
    public String matchUser(@RequestParam Long userId) {
        User currentUser = userService.getCurrentUser();
        Optional<User> otherUserOpt = userService.findById(userId);

        if (otherUserOpt.isPresent()) {
            matchService.handleMatch(currentUser, otherUserOpt.get());
        }

        return "redirect:/findBuddy";
    }

    @PostMapping("/findBuddy/decline")
    public String declineUser(@RequestParam Long userId) {
        User currentUser = userService.getCurrentUser();
        Optional<User> otherUserOpt = userService.findById(userId);

        if (otherUserOpt.isPresent()) {
            matchService.handleDecline(currentUser, otherUserOpt.get());
        }

        return "redirect:/findBuddy";
    }
}
