package com.example.quiz_app_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuestionsConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;



}
