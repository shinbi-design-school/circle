package com.design_shinbi.circle.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * image系フィールドはnullになる場合があること、ファイル名フィールドが必要なこと、Blobからの変更が必要なことから保留
 * */

public class Question {
	private String sentence;
	private String genre;
	private String correct;
	private String choice2;
	private String choice3;
	private String choice4;
	private List<String> choices;
	private String answered;
//	private Blob imagec;
//	private Blob image2;
//	private Blob image3;
//	private Blob image4;

	public Question(String sentence, String genre, String correct, String choice2, String choice3, String choice4) {
		this.sentence = sentence;
		this.genre = genre;
		this.correct = correct;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.choices = Arrays.asList(correct, choice2, choice3, choice4);
		this.shuffle();
		//this.imagec = imagec;
		//this.image2 = image2;
		//this.image3 = image3;
		//this.image4 = image4;
	}
	
	public String getSentence() {
		return sentence;
	}

	public String getGenre() {
		return genre;
	}

	public List<String> getChoices() {
		return choices;
	}
	
	public void setAnswered(String answer) {
		this.answered = answer;
	}
	
	public void shuffle() {
		Collections.shuffle(choices);
	}
	
	public boolean isCorrect(String userChoice) {
		return correct.equals(userChoice);
	}

	public static Question createQuestion(String sentence, String genre, String correct, String choice2,
			String choice3, String choice4) {
		Question question = new Question(sentence, genre, correct, choice2, choice3, choice4);
		return question;
	}

//	public static Question createQuestion(String sentence, String genre, String correct, String choice2,
//			String choice3, String choice4, Blob imagec, Blob image2, Blob image3, Blob image4) {
//		Question question = new Question(sentence, genre, correct, choice2, choice3, choice4, 
//				imagec, image2, image3, image4);
//		return question;
//	}
	
	@Override
	public String toString() {
		return sentence;
	}
	
}
