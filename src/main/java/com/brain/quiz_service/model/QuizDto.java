package com.brain.quiz_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDto {
	private String category;
	private int numQ;
	private String title;
}
