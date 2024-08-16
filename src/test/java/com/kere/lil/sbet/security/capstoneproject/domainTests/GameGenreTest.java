package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class GameGenreTest {

    @Test
    void testGameGenreProperties() {
        GameGenre genre = new GameGenre();
        genre.setName("Action");

        assertEquals("Action", genre.getName());
    }

    @Test
    void testGameGenreGames() {
        GameGenre genre = new GameGenre();
        Game game = new Game();
        game.setName("Test Game");

        genre.setGames(new HashSet<>());
        genre.getGames().add(game);

        assertTrue(genre.getGames().contains(game));
    }
}
