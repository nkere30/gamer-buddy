package com.kere.lil.sbet.security.capstoneproject.loader;

import com.kere.lil.sbet.security.capstoneproject.data.GameGenreRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Configuration
public class GameGenreLoader implements CommandLineRunner {

    @Autowired
    private GameGenreRepository gameGenreRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load the file from the resources directory
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/game_genres.txt")))) {

            String line;

            while ((line = br.readLine()) != null) {
                String genre = line.trim();
                if (!genre.isEmpty()) {
                    // Check if the genre already exists
                    Optional<GameGenre> existingGenre = gameGenreRepository.findByName(genre);
                    if (existingGenre.isEmpty()) {
                        GameGenre gameGenre = new GameGenre();
                        gameGenre.setName(genre);
                        gameGenreRepository.save(gameGenre);
                    }
                }
            }
        }
    }
}
