package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testGameProperties() {
        Game game = new Game();
        game.setName("Test Game");
        game.setDescription("Test Description");

        assertEquals("Test Game", game.getName());
        assertEquals("Test Description", game.getDescription());
    }
}
