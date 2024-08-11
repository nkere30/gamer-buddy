package com.kere.lil.sbet.security.capstoneproject;

import com.kere.lil.sbet.security.capstoneproject.data.UserRepository;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("encodedPassword", registeredUser.getPassword());
    }

    @Test
    void findByUsername_UserExists() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername(username);

        assertTrue(foundUser.isPresent());
        assertEquals(username, foundUser.get().getUsername());
    }

    @Test
    void findByUsername_UserDoesNotExist() {
        String username = "nonexistentuser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findByUsername(username);

        assertFalse(foundUser.isPresent());
    }
}
