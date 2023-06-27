package com.design_shinbi.circle.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String questionText;
	private String correctText;
	private List<String> wrongTexts;
	private String correctImg;
	private List<String> wrongImgs;
	
	/*
	 * ファクトリークラス
	 * Quizクラスから呼んでリストをもらう
	 */
	static public List<Question> createQuestions() {
		List<Question> questions = new ArrayList<>();
		return questions;
	}
	
}
