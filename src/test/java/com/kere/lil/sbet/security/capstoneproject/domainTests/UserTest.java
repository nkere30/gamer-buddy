package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserProperties() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFullName("Test User");
        user.setFavoriteGame("Game1");
        user.setFavoriteGameGenre("Genre1");

        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("Test User", user.getFullName());
        assertEquals("Game1", user.getFavoriteGame());
        assertEquals("Genre1", user.getFavoriteGameGenre());
    }
}
