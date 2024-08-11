package com.kere.lil.sbet.security.capstoneproject.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "matches")
public class Match {

    public Match(Long user1Id, Long user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long user1Id;

    @Column(nullable = false)
    private Long user2Id;
}
