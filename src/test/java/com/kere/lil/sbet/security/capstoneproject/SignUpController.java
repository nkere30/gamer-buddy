package com.kere.lil.sbet.security.capstoneproject;

import com.kere.lil.sbet.security.capstoneproject.controller.SignupController;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignupController.class)
class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signupForm_DisplaysSignupForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void registerUser_ValidUser_RedirectsToProfile() throws Exception {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("password");

        when(userService.getRegistrationErrors(user)).thenReturn("");
        when(userService.registerUser(user)).thenReturn(user);

        mockMvc.perform(post("/signup")
                        .param("username", "newuser")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile?username=newuser"));
    }

    @Test
    void registerUser_ExistingUser_ShowsErrorMessage() throws Exception {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");

        when(userService.getRegistrationErrors(user)).thenReturn("Username already taken");

        mockMvc.perform(post("/signup")
                        .param("username", "existinguser")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attributeExists("error"));
    }
}
