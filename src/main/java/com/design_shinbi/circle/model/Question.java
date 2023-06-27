package com.design_shinbi.circle.model;

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
//		this.imagec = imagec;
//		this.image2 = image2;
//		this.image3 = image3;
//		this.image4 = image4;
	}
	
	public String getSentence() {
		return sentence;
	}

	public String getGenre() {
		return genre;
	}

	public String getCorrect() {
		return correct;
	}

	public String getChoice2() {
		return choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public static Question createQuestion(String sentence, String genre, String correct, String choice2,
			String choice3, String choice4) {
		Question question = new Question(sentence, genre, correct, choice2, choice3, choice4);
		return null;
	}

//	public static Question createQuestion(String sentence, String genre, String correct, String choice2,
//			String choice3, String choice4, Blob imagec, Blob image2, Blob image3, Blob image4) {
//		Question question = new Question(sentence, genre, correct, choice2, choice3, choice4, 
//				imagec, image2, image3, image4);
//		return question;
//	}

	
}
