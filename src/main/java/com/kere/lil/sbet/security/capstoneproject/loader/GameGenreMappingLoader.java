package com.kere.lil.sbet.security.capstoneproject.loader;

import com.kere.lil.sbet.security.capstoneproject.data.GameRepository;
import com.kere.lil.sbet.security.capstoneproject.data.GameGenreRepository;
import com.kere.lil.sbet.security.capstoneproject.data.GameGenreMappingRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenreMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Configuration
public class GameGenreMappingLoader implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameGenreRepository gameGenreRepository;

    @Autowired
    private GameGenreMappingRepository gameGenreMappingRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Load the file from the resources directory
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/game_genre_mapping.txt")))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                String gameName = parts[0].trim();
                String genreName = parts[1].trim();

                // Find the game and genre from the repositories
                Optional<Game> gameOpt = gameRepository.findByName(gameName);
                Optional<GameGenre> genreOpt = gameGenreRepository.findByName(genreName);

                if (gameOpt.isPresent() && genreOpt.isPresent()) {
                    Game game = gameOpt.get();
                    GameGenre genre = genreOpt.get();

                    // Create and save the GameGenreMapping entity
                    GameGenreMapping mapping = new GameGenreMapping();
                    mapping.setGame(game);
                    mapping.setGameGenre(genre);
                    gameGenreMappingRepository.save(mapping);
                } else {
                    System.out.println("Game or Genre not found for: " + gameName + ", " + genreName);
                }
            }
        }
    }
}
