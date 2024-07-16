package com.example.quiz_app_backend.Repository;

import com.example.quiz_app_backend.Entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

}


