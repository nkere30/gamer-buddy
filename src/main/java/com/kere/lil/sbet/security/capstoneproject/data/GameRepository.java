package com.kere.lil.sbet.security.capstoneproject.data;

import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByNameContainingIgnoreCase(String name);
}
