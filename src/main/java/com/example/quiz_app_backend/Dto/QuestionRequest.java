package com.example.quiz_app_backend.Dto;

import lombok.Data;

@Data
public class QuestionRequest {
    private long subjectid;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;


}
