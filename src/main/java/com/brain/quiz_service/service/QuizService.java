package com.brain.quiz_service.service;

import com.brain.quiz_service.model.QuestionWrapper;
import com.brain.quiz_service.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);

	ResponseEntity<List<QuestionWrapper>> getQuizById(Long quizId);

	ResponseEntity<Integer> getQuizResult(Long quizId, List<Response> response);
}
