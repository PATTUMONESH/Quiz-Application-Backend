package com.example.quiz_app_backend.Service.Impl;


import com.example.quiz_app_backend.Entity.UserDetails;
import com.example.quiz_app_backend.Entity.UserScore;
import com.example.quiz_app_backend.Repository.UserRepository;
import com.example.quiz_app_backend.Repository.UserScoreRepository;
import com.example.quiz_app_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

@Autowired
    private UserScoreRepository userScoreRepository;





    public  UserImpl(UserRepository userRepository,UserScoreRepository userScoreRepository){
        this.userScoreRepository=userScoreRepository;
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails saveUser(UserDetails userDetails) {

        return userRepository.save(userDetails);
    }

    @Override
    public UserDetails fetchUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails fetchUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public UserScore saveUserScore(UserScore userScore, Long userId) {
        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userScore.setUserDetails(userDetails);
        return userScoreRepository.save(userScore);
    }


    public List<UserScore> getAllUserScores() {
        return userScoreRepository.findAll();
    }


}
