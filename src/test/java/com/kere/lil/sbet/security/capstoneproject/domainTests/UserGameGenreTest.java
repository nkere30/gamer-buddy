package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.GameGenre;
import com.kere.lil.sbet.security.capstoneproject.domain.User;
import com.kere.lil.sbet.security.capstoneproject.domain.UserGameGenre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserGameGenreTest {

    @Test
    void testUserGameGenreMapping() {
        User user = new User();
        user.setUsername("testuser");

        GameGenre genre = new GameGenre();
        genre.setName("Action");

        UserGameGenre userGameGenre = new UserGameGenre();
        userGameGenre.setUser(user);
        userGameGenre.setGameGenre(genre);

        assertEquals(user, userGameGenre.getUser());
        assertEquals(genre, userGameGenre.getGameGenre());
    }
}
