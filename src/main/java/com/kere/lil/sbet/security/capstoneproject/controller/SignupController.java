package com.kere.lil.sbet.security.capstoneproject.controller;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.GameGenreService;
import com.kere.lil.sbet.security.capstoneproject.service.GameService;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    @Autowired
    private GameGenreService gameGenreService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        // Use the services to fetch games and genres
        List<Game> games = gameService.getAllGames();
        List<GameGenre> gameGenres = gameGenreService.getAllGameGenres();
        model.addAttribute("games", games);
        model.addAttribute("gameGenres", gameGenres);
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Get the error messages, ensure it's not null
        String errorMessages = userService.getRegistrationErrors(user);

        // Check if errorMessages is not null and not empty
        if (errorMessages != null && !errorMessages.isEmpty()) {
            model.addAttribute("error", errorMessages);
            return "signup";
        }

        // Register the user
        userService.registerUser(user);

        // Authenticate and log in the newly registered user
        userService.authenticateAndLoginUser(user, authenticationManager);

        // Redirect to the profile page of the newly logged-in user
        return "redirect:/profile";
    }

}
