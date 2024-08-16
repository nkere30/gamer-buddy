package com.kere.lil.sbet.security.capstoneproject.serviceTests;

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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        when(matchRepository.findFirstByUser1IdAndUser2Id(user1.getId(), user2.getId())).thenReturn(Optional.empty());
        when(matchRepository.save(any(Match.class))).thenReturn(new Match(user1.getId(), user2.getId()));

        Match match = matchService.handleMatch(user1, user2);

        assertNotNull(match);
        verify(matchRepository, times(1)).save(any(Match.class));
    }

    @Test
    void handleDecline_Success() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        matchService.handleDecline(user1, user2);

        verify(matchRepository, times(1)).save(any(Match.class));
    }

    @Test
    void getRandomUserForMatching_ReturnsUser() {
        User currentUser = new User();
        currentUser.setId(1L);
        currentUser.setFavoriteGame("GameA");
        currentUser.setFavoriteGameGenre("GenreA");

        User otherUser = new User();
        otherUser.setId(2L);
        otherUser.setFavoriteGame("GameA");
        otherUser.setFavoriteGameGenre("GenreA");

        when(userRepository.findAll()).thenReturn(List.of(currentUser, otherUser));

        Optional<User> result = matchService.getRandomUserForMatching(currentUser);

        assertTrue(result.isPresent());
        assertEquals(otherUser.getId(), result.get().getId());
    }
}
