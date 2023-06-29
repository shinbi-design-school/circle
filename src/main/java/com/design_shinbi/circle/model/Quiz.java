package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.design_shinbi.circle.model.dao.QuizDAO;

public class Quiz {
	private List<Question> questions;
	private int correctCount;
	private QuizDAO dao;
	private String state;
	private int answered;
	private LocalDateTime startTime;
	private LocalDateTime finishTime;
		
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
		this.state = "standby";
		this.startTime = LocalDateTime.now();
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void chkState() {
		if (this.getAnswered() >= Const.QUIZ_CHOICE_VALUE) {
			if (this.getState().equals("playing")) {
				this.setState("finish");
			} else {
				this.setState("standby");
			}
		} else {
			if (this.getState().equals("finish")){
				this.setState("standby");
			}
		}
		
		if (this.getState().equals("standby")) {
			this.setAnswered(0);
		}
		
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
		
	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setQuestions() throws SQLException {
		this.questions = this.dao.choiceQuestions();
	}
	
	public Question pick() {
		return questions.get(this.getAnswered());
	}
}
