package com.kere.lil.sbet.security.capstoneproject.controllerTests;

import com.kere.lil.sbet.security.capstoneproject.controller.ProfileController;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void profile_ValidUser_DisplaysProfile() throws Exception {
        User user = new User();
        user.setUsername("testuser");

        when(userService.findByUsername("testuser")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/profile")
                        .param("username", "testuser")
                        .with(user("testuser").roles("USER")))  // Mock authenticated user
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void profile_InvalidUser_RedirectsToLogin() throws Exception {
        when(userService.findByUsername("invaliduser")).thenReturn(Optional.empty());

        mockMvc.perform(get("/profile")
                        .param("username", "invaliduser")
                        .with(user("invaliduser").roles("USER")))  // Mock authenticated user
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
