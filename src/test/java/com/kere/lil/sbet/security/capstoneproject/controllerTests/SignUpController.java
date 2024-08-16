package com.kere.lil.sbet.security.capstoneproject.controllerTests;

import com.kere.lil.sbet.security.capstoneproject.controller.SignupController;
import com.kere.lil.sbet.security.capstoneproject.domain.Game;
import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.GameGenreService;
import com.kere.lil.sbet.security.capstoneproject.service.GameService;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(SignupController.class)
@AutoConfigureMockMvc(addFilters = false)  // Disable security filters for testing
class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private GameService gameService;

    @MockBean
    private GameGenreService gameGenreService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser  // Simulate an authenticated user
    void signupForm_DisplaysSignupForm() throws Exception {
        when(gameService.getAllGames()).thenReturn(List.of(new Game()));
        when(gameGenreService.getAllGameGenres()).thenReturn(List.of(new GameGenre()));

        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("games"))
                .andExpect(model().attributeExists("gameGenres"));
    }

    @Test
    @WithMockUser  // Simulate an authenticated user
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
                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    @WithMockUser(roles = "USER")  // Simulate a user with the "USER" role
    void registerUser_ExistingUser_ShowsErrorMessage() throws Exception {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");

        // Mocking the service to return an error message indicating the username is taken
        when(userService.getRegistrationErrors(user)).thenReturn("Username already taken");

        mockMvc.perform(post("/signup")
                        .param("username", "existinguser")
                        .param("password", "password"))
                .andExpect(status().isOk())  // Expecting the status to be 200 OK
                .andExpect(view().name("signup"))  // Expecting to stay on the signup page
                .andExpect(model().attributeExists("error"));  // Expecting the error attribute in the model
    }


}
