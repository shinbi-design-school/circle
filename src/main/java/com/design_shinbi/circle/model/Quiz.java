package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.List;

import com.design_shinbi.circle.model.dao.QuizDAO;

public class Quiz {
	private List<Question> questions;
	private int correctCount;
	private QuizDAO dao;
	private List<Integer> history;
	private String state;
	private int answered;
		
	public Quiz(QuizDAO dao) throws SQLException {
		this.dao = dao;
		this.init();
	}
	
	public void init() throws SQLException {
		this.questions = dao.choiceQuestions();
		//データベースのrand()でシャッフルされているのでコメントアウト
		//Collections.shuffle(questions);
		this.correctCount = 0;
		this.answered = 0;
		this.state = null;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public int getAnswered() {
		return answered;
	}

	public void setAnswered(int answered) {
		this.answered = answered;
	}

	public int getCorrectCount() {
		return correctCount;
	}
	
	public double getCorrectRate() {
		return getCorrectCount() / questions.size(); 
	}
	
	public void setQuestions() throws SQLException {
		this.questions = this.dao.choiceQuestions();
	}
}
