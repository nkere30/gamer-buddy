package com.kere.lil.sbet.security.capstoneproject.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_game_genres")
@IdClass(UserGameGenreId.class) // Link to the composite key class
public class UserGameGenre {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_genre_id")
    private GameGenre gameGenre;
}

