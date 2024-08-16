package com.kere.lil.sbet.security.capstoneproject.serviceTests;

import com.kere.lil.sbet.security.capstoneproject.data.UserRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        Optional<User> authenticatedUser = userService.authenticateUser("testuser", "password");

        assertTrue(authenticatedUser.isPresent());
    }

    @Test
    void authenticateUser_Failure() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        Optional<User> authenticatedUser = userService.authenticateUser("testuser", "password");

        assertFalse(authenticatedUser.isPresent());
    }

    @Test
    void authenticateAndLoginUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        userService.authenticateAndLoginUser(user, authenticationManager);

        verify(authenticationManager, times(1)).authenticate(any());
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void getRegistrationErrors_UsernameTaken() {
        User user = new User();
        user.setUsername("testuser");

        when(userService.isUsernameTaken("testuser")).thenReturn(true);

        String errors = userService.getRegistrationErrors(user);

        assertEquals("Username already taken", errors);
    }

    @Test
    void getRegistrationErrors_EmailTaken() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userService.isEmailTaken("test@example.com")).thenReturn(true);

        String errors = userService.getRegistrationErrors(user);

        assertEquals("Email already taken", errors);
    }
}
