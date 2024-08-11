package com.kere.lil.sbet.security.capstoneproject.service;

import com.kere.lil.sbet.security.capstoneproject.data.UserRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerUser(User user) {
        logger.info("Registering user: {}", user.getUsername());
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        logger.info("Finding user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    public Optional<User> authenticateUser(String usernameOrEmail, String password) {
        logger.info("Authenticating user with username/email: {}", usernameOrEmail);
        Optional<User> user = userRepository.findByUsername(usernameOrEmail);

        if (user.isEmpty()) {
            logger.info("User not found by username. Trying email.");
            user = userRepository.findByEmail(usernameOrEmail);
        }

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            logger.info("Authentication successful for user: {}", usernameOrEmail);
            return user;
        } else {
            logger.warn("Authentication failed for user: {}", usernameOrEmail);
            return Optional.empty();
        }
    }

    public boolean isUsernameTaken(String username) {
        logger.info("Checking if username {} is taken", username);
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailTaken(String email) {
        logger.info("Checking if email {} is taken", email);
        return userRepository.findByEmail(email).isPresent();
    }

    public String getRegistrationErrors(User user) {
        logger.info("Validating registration for user: {}", user.getUsername());
        StringBuilder errors = new StringBuilder();

        if (isUsernameTaken(user.getUsername())) {
            errors.append("Username already taken");
            logger.warn("Username {} is already taken", user.getUsername());
        }

        if (isEmailTaken(user.getEmail())) {
            if (errors.length() > 0) errors.append(" and ");
            errors.append("Email already taken");
            logger.warn("Email {} is already taken", user.getEmail());
        }

        return errors.toString();
    }
}
