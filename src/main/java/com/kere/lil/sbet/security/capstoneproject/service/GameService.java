package com.kere.lil.sbet.security.capstoneproject.service;

import com.kere.lil.sbet.security.capstoneproject.data.GameRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}
