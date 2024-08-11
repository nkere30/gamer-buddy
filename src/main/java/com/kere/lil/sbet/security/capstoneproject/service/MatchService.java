package com.kere.lil.sbet.security.capstoneproject.service;

import com.kere.lil.sbet.security.capstoneproject.data.MatchRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Match;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Match handleMatch(User currentUser, User otherUser) {
        Optional<Match> existingMatch = matchRepository.findByUser1IdAndUser2Id(currentUser.getId(), otherUser.getId());
        Match match;
        if (existingMatch.isPresent()) {
            match = existingMatch.get();
        } else {
            match = new Match(currentUser.getId(), otherUser.getId());
            match = matchRepository.save(match);
        }
        return match;
    }

    public void handleDecline(User currentUser, User otherUser) {
        // If needed, implement decline logic or leave it empty
        Match match = new Match(currentUser.getId(), otherUser.getId());
        matchRepository.save(match);
    }
}
