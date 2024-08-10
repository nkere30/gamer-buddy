package com.kere.lil.sbet.security.capstoneproject.data;

import com.kere.lil.sbet.security.capstoneproject.domain.UserGameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserGameGenreRepository extends JpaRepository<UserGameGenre, Long> {
    List<UserGameGenre> findByUserId(Long userId);
    List<UserGameGenre> findByGameGenreId(Long gameGenreId);
    List<UserGameGenre> findByUserIdAndGameGenreId(Long userId, Long gameGenreId);
}
