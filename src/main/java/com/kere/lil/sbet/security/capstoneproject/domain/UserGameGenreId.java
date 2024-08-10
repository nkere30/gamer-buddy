package com.kere.lil.sbet.security.capstoneproject.domain;

import java.io.Serializable;
import java.util.Objects;

public class UserGameGenreId implements Serializable {

    private Long user;
    private Long gameGenre;

    // Default constructor
    public UserGameGenreId() {}

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGameGenreId that = (UserGameGenreId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(gameGenre, that.gameGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, gameGenre);
    }
}
