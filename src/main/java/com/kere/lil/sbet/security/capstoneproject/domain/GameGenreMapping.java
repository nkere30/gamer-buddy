package com.kere.lil.sbet.security.capstoneproject.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "game_genre_mappings")
@IdClass(GameGenreMappingId.class)
public class GameGenreMapping {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_genre_id")
    private GameGenre gameGenre;
}

