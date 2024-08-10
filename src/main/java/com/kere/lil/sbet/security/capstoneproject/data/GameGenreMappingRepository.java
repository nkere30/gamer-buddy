package com.kere.lil.sbet.security.capstoneproject.data;

import com.kere.lil.sbet.security.capstoneproject.domain.GameGenreMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameGenreMappingRepository extends JpaRepository<GameGenreMapping, Long> {
    List<GameGenreMapping> findByGameId(Long gameId);
    List<GameGenreMapping> findByGameGenreId(Long gameGenreId);
    List<GameGenreMapping> findByGameIdAndGameGenreId(Long gameId, Long gameGenreId);
}
