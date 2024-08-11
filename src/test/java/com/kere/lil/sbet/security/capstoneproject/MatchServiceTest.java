package com.kere.lil.sbet.security.capstoneproject;

import com.kere.lil.sbet.security.capstoneproject.data.MatchRepository;
import com.kere.lil.sbet.security.capstoneproject.data.UserRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.Match;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleMatch_NewMatch() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        when(matchRepository.findByUser1IdAndUser2Id(user1.getId(), user2.getId())).thenReturn(Optional.empty());
        when(matchRepository.save(new Match(user1.getId(), user2.getId()))).thenReturn(new Match(user1.getId(), user2.getId()));

        Match match = matchService.handleMatch(user1, user2);

        assertNotNull(match);
    }
}
