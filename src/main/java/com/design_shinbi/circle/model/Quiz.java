package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.design_shinbi.circle.model.dao.QuizDAO;

public class Quiz implements Comparable<Quiz>{
	//レコード向けフィールド
	private int id;
	private int userId;
	
	//セッション向けフィールド
	private List<Question> questions;
	private int correctCount;
	private QuizDAO dao;
	private int questionsValue;
	
	/*
	 * state変数
	 * Quizが待機中か、進行中か、終了済みか表す変数
	 * standby	:待機中
	 * playing	:進行中
	 * finish	:終了済み
	 * 
	 * 列挙型か定数を使うべき？
	 */
	private String state;
	private int answered;
	private LocalDateTime startTime;
	private LocalDateTime finishTime;
	
	public Quiz() {
		this.questionsValue = 0;
	}
	
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
		this.questionsValue = Const.QUIZ_CHOICE_VALUE;
	}

	public void finish() {
		this.setFinishTime(LocalDateTime.now());
		this.setState("finish");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	//idからユーザー名を取得する
//	public String getUserName() {
//		return ;
//	}

	public List<Question> getQuestions(){
		return this.questions;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public int getQuestionsValue() {
		return this.questionsValue;
	}
	
	public void setQuestionsValue(int questionsValue) {
		if(this.questionsValue == 0) {
			this.questionsValue = questionsValue;
		}
	}
	
	public void chkState() {
		if (this.getAnswered() >= this.questionsValue) {
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
	
	public void setCorrectCount(int cnt) {
		this.correctCount = cnt;
	}

	public int getCorrectCount() {
		return correctCount;
	}
	
	public double getCorrectRate() {
		double result = (double)getCorrectCount() / (double)questions.size(); 
		return result;
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
	
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public long getElapsedTime() {
		return Duration.between(startTime, finishTime).toMillis();
	}
	
	public int getElapsedSeconds() {
		long tmp = Duration.between(startTime, finishTime).toSeconds();
		int result = tmp < Integer.MAX_VALUE ? (int)tmp : Integer.MAX_VALUE ;
		return result;
	}

	public void setQuestions() throws SQLException {
		this.questions = this.dao.choiceQuestions();
	}
	
	public Question pick() {
		return questions.get(this.getAnswered());
	}
	
	public int calcScore() {
		double result = 0;
		
		result += getCorrectCount() * 20;
		
		if (this.getElapsedTime() < 30000) {
			result += 55;
		} else if (this.getElapsedTime() < 60000 ) {
			result += 50;
		} else if(this.getElapsedTime() < 120000) {
			result += 30;
		} else {
			result += 10;
		}
		
		if (this.getCorrectRate() >= 1.0) {
			result *= 2;
		} else if (this.getCorrectRate() >= 0.7) {
			result *= 1.7;
		} else if (this.getCorrectRate() == 0.5) {
			result *= 1.5;
		} else {
			result *= 0.5;
		}
		
		return (int)result;
	}
	
	@Override
	public int compareTo(Quiz o) {
		int self = this.calcScore();
		int someone = o.calcScore(); 
		if (self > someone){
			return 1;
		} else if ( self < someone) {
			return -1;
		}
		return 0;
	}
	
}
