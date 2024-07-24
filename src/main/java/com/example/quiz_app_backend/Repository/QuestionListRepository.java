package com.example.quiz_app_backend.Repository;

import com.example.quiz_app_backend.Entity.QuestionsConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionListRepository extends JpaRepository<QuestionsConfig,Integer> {

}

