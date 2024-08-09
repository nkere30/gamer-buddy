package com.kere.lil.sbet.security.capstoneproject.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String fullName;
    // Getters and Setters
}