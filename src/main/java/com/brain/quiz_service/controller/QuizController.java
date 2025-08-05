package com.brain.quiz_service.controller;

import com.brain.quiz_service.model.QuestionWrapper;
import com.brain.quiz_service.model.Quiz;
import com.brain.quiz_service.model.QuizDto;
import com.brain.quiz_service.model.Response;
import com.brain.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
	}

	@GetMapping("getQuiz/{quizId}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Long quizId) {
		return quizService.getQuizById(quizId);
	}

	@PostMapping("submit/{quizId}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<Response> response) {
		return quizService.getQuizResult(quizId, response);
	}

	@GetMapping("getAll")
	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		return quizService.getAllQuizzes();
	}
}
