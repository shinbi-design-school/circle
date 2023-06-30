package com.design_shinbi.circle.model.entity;

import java.sql.Timestamp;

public class Score {
	private int userId;
	private int time;
	private int correctCount;
	private int questionValue;
	private Timestamp timestamp;
		
	public int getUserName() {
		return userId;
	}
	public void setUserName(int userId) {
		this.userId = userId;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCorrectCount() {
		return correctCount;
	}
	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}
	public int getQuestionValue() {
		return questionValue;
	}
	public void setQuestionValue(int questionValue) {
		this.questionValue = questionValue;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	
	
}
