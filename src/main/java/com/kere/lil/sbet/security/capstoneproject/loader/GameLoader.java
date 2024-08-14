package com.kere.lil.sbet.security.capstoneproject.loader;

import com.kere.lil.sbet.security.capstoneproject.data.GameRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Configuration
public class GameLoader implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load the file from the resources directory
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/games.txt")))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String name = parts[0].trim();
                String description = parts.length > 1 ? parts[1].trim() : "";

                if (!name.isEmpty()) {
                    // Use findByName to check if the game already exists
                    Optional<Game> existingGame = gameRepository.findByName(name);
                    if (existingGame.isEmpty()) {
                        Game game = new Game();
                        game.setName(name);
                        game.setDescription(description);
                        gameRepository.save(game);
                    }
                }
            }
        }
    }
}
