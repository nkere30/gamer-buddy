package com.kere.lil.sbet.security.capstoneproject.data;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
}
