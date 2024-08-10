package com.kere.lil.sbet.security.capstoneproject.data;

import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GameGenreRepository extends JpaRepository<GameGenre, Long> {
    Optional<GameGenre> findByName(String name);
}

