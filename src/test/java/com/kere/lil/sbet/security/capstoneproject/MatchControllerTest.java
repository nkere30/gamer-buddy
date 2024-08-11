package com.kere.lil.sbet.security.capstoneproject;

import com.kere.lil.sbet.security.capstoneproject.controller.MatchController;
import com.kere.lil.sbet.security.capstoneproject.service.MatchService;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    void findBuddyPage_AuthenticatedUser() throws Exception {
        mockMvc.perform(get("/findBuddy"))
                .andExpect(status().isOk())
                .andExpect(view().name("findBuddy"));
    }
}
