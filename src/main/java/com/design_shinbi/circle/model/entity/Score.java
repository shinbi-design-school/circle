package com.design_shinbi.circle.model.entity;

import java.sql.Timestamp;

public class Score {
	private int userId;
	private long time;
	private int correctValue;
	private int questionValue;
	private Timestamp timestamp;
		
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCorrectCount() {
		return correctValue;
	}
	public void setCorrectCount(int correctValue) {
		this.correctValue = correctValue;
	}
	public int getQuestionValue() {
		return questionValue;
	}
	public void setQuestionValue(int questionValue) {
		this.questionValue = questionValue;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	
	
}
