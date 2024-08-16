package com.kere.lil.sbet.security.capstoneproject.service;

import com.kere.lil.sbet.security.capstoneproject.data.GameGenreRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameGenreService {

    @Autowired
    private GameGenreRepository gameGenreRepository;

    public List<GameGenre> getAllGameGenres() {
        return gameGenreRepository.findAll();
    }
}
