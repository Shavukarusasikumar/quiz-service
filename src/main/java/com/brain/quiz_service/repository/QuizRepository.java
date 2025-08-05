package com.brain.quiz_service.repository;

import com.brain.quiz_service.model.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
