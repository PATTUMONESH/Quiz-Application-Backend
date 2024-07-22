package com.example.quiz_app_backend.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailStructure {
    private String name;
    private String subject;
    private String message;
    private long score;
    private int correctAnswer;
   private int wrongAnswer;
   private int unAtteptedQuestion;
    private int totalQuestions;
    private String description;


}
