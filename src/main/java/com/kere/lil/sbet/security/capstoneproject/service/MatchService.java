package com.kere.lil.sbet.security.capstoneproject.service;

import com.kere.lil.sbet.security.capstoneproject.data.MatchRepository;
import com.kere.lil.sbet.security.capstoneproject.data.UserRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Match;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private UserRepository userRepository;



    public Match handleMatch(User currentUser, User otherUser) {
        // Use findFirstByUser1IdAndUser2Id to avoid NonUniqueResultException
        Optional<Match> existingMatch = matchRepository.findFirstByUser1IdAndUser2Id(currentUser.getId(), otherUser.getId());
        Match match;
        if (existingMatch.isPresent()) {
            match = existingMatch.get();
        } else {
            match = new Match(currentUser.getId(), otherUser.getId());
            match = matchRepository.save(match);
        }
        return match;
    }


    public Optional<User> getRandomUserForMatching(User currentUser) {
        // Fetch all users with the same favorite game or genre except the current user
        List<User> potentialMatches = userRepository.findAll().stream()
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .filter(user -> user.getFavoriteGame().equals(currentUser.getFavoriteGame()) ||
                        user.getFavoriteGameGenre().equals(currentUser.getFavoriteGameGenre()))
                .collect(Collectors.toList());

        if (potentialMatches.isEmpty()) {
            return Optional.empty();
        }

        // Randomly select a user from the list
        Random random = new Random();
        User randomUser = potentialMatches.get(random.nextInt(potentialMatches.size()));

        return Optional.of(randomUser);
    }

    public void handleDecline(User currentUser, User otherUser) {
        // If needed, implement decline logic or leave it empty
        Match match = new Match(currentUser.getId(), otherUser.getId());
        matchRepository.save(match);
    }


}
