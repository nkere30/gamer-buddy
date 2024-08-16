package com.kere.lil.sbet.security.capstoneproject.serviceTests;

import com.kere.lil.sbet.security.capstoneproject.data.GameGenreRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import com.kere.lil.sbet.security.capstoneproject.service.GameGenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameGenreServiceTest {

    @InjectMocks
    private GameGenreService gameGenreService;

    @Mock
    private GameGenreRepository gameGenreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGameGenres_ReturnsListOfGenres() {
        GameGenre genre1 = new GameGenre();
        genre1.setName("Action");

        GameGenre genre2 = new GameGenre();
        genre2.setName("Adventure");

        when(gameGenreRepository.findAll()).thenReturn(Arrays.asList(genre1, genre2));

        List<GameGenre> genres = gameGenreService.getAllGameGenres();

        assertEquals(2, genres.size());
        assertTrue(genres.contains(genre1));
        assertTrue(genres.contains(genre2));
    }
}
