package com.kere.lil.sbet.security.capstoneproject.controllerTests;

import com.kere.lil.sbet.security.capstoneproject.controller.MatchController;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.MatchService;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MatchController.class)
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MatchService matchService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void declineUser_NewMatchFound() throws Exception {
        User currentUser = new User();
        currentUser.setId(1L);
        User newMatch = new User();
        newMatch.setId(2L);

        when(userService.getCurrentUser()).thenReturn(currentUser);
        when(userService.findById(2L)).thenReturn(Optional.of(newMatch));
        when(matchService.getRandomUserForMatching(currentUser)).thenReturn(Optional.of(newMatch));

        mockMvc.perform(post("/findBuddy/decline")
                        .param("userId", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("findBuddy"));
    }

    @Test
    void declineUser_NoNewMatchFound() throws Exception {
        User currentUser = new User();
        currentUser.setId(1L);

        when(userService.getCurrentUser()).thenReturn(currentUser);
        when(matchService.getRandomUserForMatching(currentUser)).thenReturn(Optional.empty());

        mockMvc.perform(post("/findBuddy/decline")
                        .param("userId", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("noMatches"));
    }
}
