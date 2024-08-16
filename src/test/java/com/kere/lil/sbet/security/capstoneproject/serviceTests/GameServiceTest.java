package com.kere.lil.sbet.security.capstoneproject.serviceTests;

import com.kere.lil.sbet.security.capstoneproject.data.GameRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGames_ReturnsListOfGames() {
        Game game1 = new Game();
        game1.setName("Game 1");

        Game game2 = new Game();
        game2.setName("Game 2");

        when(gameRepository.findAll()).thenReturn(Arrays.asList(game1, game2));

        List<Game> games = gameService.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
    }
}
