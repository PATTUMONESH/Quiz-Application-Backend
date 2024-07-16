package com.example.quiz_app_backend.Service;


import com.example.quiz_app_backend.Entity.UserDetails;
import com.example.quiz_app_backend.Entity.UserScore;
import com.example.quiz_app_backend.Repository.UserRepository;

import java.util.List;

public interface UserService {


    UserDetails saveUser(UserDetails userDetails);

    UserDetails fetchUserByEmail(String email);

    UserDetails fetchUserByEmailAndPassword(String email,String password);


    UserScore saveUserScore(UserScore userScore,Long userId);

     List<UserScore> getAllUserScores();








}
