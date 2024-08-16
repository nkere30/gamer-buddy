package com.kere.lil.sbet.security.capstoneproject.data;

import com.kere.lil.sbet.security.capstoneproject.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findFirstByUser1IdAndUser2Id(Long user1Id, Long user2Id);
}
