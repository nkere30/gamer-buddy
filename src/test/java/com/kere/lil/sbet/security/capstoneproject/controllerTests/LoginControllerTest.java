package com.kere.lil.sbet.security.capstoneproject.controllerTests;

import com.kere.lil.sbet.security.capstoneproject.controller.LoginController;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginForm_DisplaysLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void loginUser_ValidUser_RedirectsToProfile() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.authenticateUser("testuser", "password")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/login")
                        .param("username", "testuser")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile?username=testuser"));
    }

    @Test
    void loginUser_InvalidUser_ShowsErrorMessage() throws Exception {
        when(userService.authenticateUser("wronguser", "password")).thenReturn(Optional.empty());

        mockMvc.perform(post("/login")
                        .param("username", "wronguser")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}
