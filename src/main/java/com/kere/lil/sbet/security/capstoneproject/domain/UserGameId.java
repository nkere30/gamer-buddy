package com.kere.lil.sbet.security.capstoneproject.domain;

import java.io.Serializable;
import java.util.Objects;

public class UserGameId implements Serializable {

    private Long user;
    private Long game;

    // Default constructor
    public UserGameId() {}

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGameId that = (UserGameId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, game);
    }
}
