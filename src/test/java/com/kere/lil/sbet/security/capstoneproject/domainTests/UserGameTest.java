package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.domain.UserGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserGameTest {

    @Test
    void testUserGameMapping() {
        User user = new User();
        user.setUsername("testuser");

        Game game = new Game();
        game.setName("Test Game");

        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGame(game);

        assertEquals(user, userGame.getUser());
        assertEquals(game, userGame.getGame());
    }
}
