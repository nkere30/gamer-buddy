package com.kere.lil.sbet.security.capstoneproject.domain;

import java.io.Serializable;
import java.util.Objects;

public class GameGenreMappingId implements Serializable {

    private Long game;
    private Long gameGenre;

    // Default constructor
    public GameGenreMappingId() {}

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameGenreMappingId that = (GameGenreMappingId) o;
        return Objects.equals(game, that.game) &&
                Objects.equals(gameGenre, that.gameGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, gameGenre);
    }
}
