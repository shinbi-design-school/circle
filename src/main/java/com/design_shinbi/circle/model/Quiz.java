package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.List;

import com.design_shinbi.circle.model.dao.QuizDAO;

public class Quiz {
	private List<Question> questions;
	private int correctCount;
	private QuizDAO dao;
	private List<Integer> history;
	
	/*
	 * state
	 * 0:クイズスタート前
	 * 1:クイズ出題
	 * 2:クイズ答え合わせ
	 * 4:クイズ終了
	 */
	private int state;
		
	public Quiz(QuizDAO dao) throws SQLException {
		this.dao = dao;
		this.init();
	}
	
	public void init() throws SQLException {
		this.questions = dao.choiceQuestions();
		//データベースのrand()でシャッフルされているのでコメントアウト
		//Collections.shuffle(questions);
		correctCount = 0;
		state = 0;
	}
	
	public Question ask() {
		
		return null;
	}
	
	public int getState() {
		return state;
	}
	
	public int getCorrectCount() {
		return correctCount;
	}
}
