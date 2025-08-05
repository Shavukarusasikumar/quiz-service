package com.brain.quiz_service.service.serviceImpl;

import com.brain.quiz_service.feign.QuizInterface;
import com.brain.quiz_service.model.QuestionWrapper;
import com.brain.quiz_service.model.Quiz;
import com.brain.quiz_service.model.QuizDto;
import com.brain.quiz_service.model.Response;
import com.brain.quiz_service.repository.QuizRepository;
import com.brain.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;
	private final QuizInterface quizInterface;
//	private final QuestionRepository questionRepository;

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);

			quizRepository.save(quiz);

			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizById(Long quizId) {
		try {
			Optional<Quiz> quiz = quizRepository.findById(quizId);

			if (!quiz.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			List<Integer> questionsFromDb = quiz.get().getQuestions();
			List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsById(questionsFromDb).getBody();

			return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<Integer> getQuizResult(Long quizId, List<Response> response) {
		try {
			return new ResponseEntity<>(quizInterface.getScore(response).getBody(), HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		try {
			List<Quiz> quizzes = quizRepository.findAll();
			return new ResponseEntity<>(quizzes, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
