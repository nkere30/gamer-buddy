package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenreMapping;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameGenreMappingTest {

    @Test
    void testGameGenreMapping() {
        Game game = new Game();
        game.setName("Test Game");

        GameGenre genre = new GameGenre();
        genre.setName("Action");

        GameGenreMapping mapping = new GameGenreMapping();
        mapping.setGame(game);
        mapping.setGameGenre(genre);

        assertEquals(game, mapping.getGame());
        assertEquals(genre, mapping.getGameGenre());
    }
}
