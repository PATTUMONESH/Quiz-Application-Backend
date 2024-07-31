package com.example.quiz_app_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
@Entity
public class UserScore {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private long userId;
//    private String firstName;
//    private int UserScore;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_score_seq")
    @SequenceGenerator(name = "user_score_seq", sequenceName = "user_score_sequence", initialValue = 101, allocationSize = 1)
    private long id;
    private String firstName;
    private String lastName;
    private int score;
    private int correct;
    private int inCorrect;
    private int notVisited;
    private int total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails userDetails;


}
