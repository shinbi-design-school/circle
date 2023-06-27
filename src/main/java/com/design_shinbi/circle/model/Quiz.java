package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.List;

import com.design_shinbi.circle.model.dao.QuizDAO;

public class Quiz {
	private List<Question> questions;
	private int correctCount;
	private QuizDAO dao;
	private int state;
		
	public Quiz(QuizDAO dao) {
		this.dao = dao;
	}
	
	public void init() throws SQLException {
		this.questions = dao.choiceQuestions();
		//データベースのrand()でシャッフルされているのでコメントアウト
		//Collections.shuffle(questions);
	}
	
	public String ask() {
		return null;
	}
	
	
	public int getCorrectCount() {
		return correctCount;
	}
}
