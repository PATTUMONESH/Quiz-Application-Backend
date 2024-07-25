package com.example.quiz_app_backend.Repository;

import com.example.quiz_app_backend.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
