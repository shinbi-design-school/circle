package com.design_shinbi.circle.model.entity;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
	private int correctIndex;
	private int userAnswered;
//	private Blob imagec;
//	private Blob image2;
//	private Blob image3;
//	private Blob image4;
	private String token;

	public Question(String sentence, String genre, String correct, String choice2, String choice3, String choice4) throws NoSuchAlgorithmException {
		this.sentence = sentence;
		this.genre = genre;
		this.correct = correct;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.choices = Arrays.asList(this.correct, this.choice2, this.choice3, this.choice4);
		this.shuffle();
		this.userAnswered = -1;
		//this.imagec = imagec;
		//this.image2 = image2;
		//this.image3 = image3;
		//this.image4 = image4;
		this.setToken();
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
	
	public void setUserAnswered(String answerNumber) {
		if (this.getUserAnswered() == -1) {			
			this.userAnswered = Integer.parseInt(answerNumber);
		}
	}
	
	public int getUserAnswered() {
		return this.userAnswered;
	}	
	
	public String getUserAnsweredText(int index) {
		if (index < 0 || index > choices.size()) {
			return "指定された選択肢が存在しません";
		}
		return this.getChoices().get(index);
	}
	
	private void shuffle() {
		Collections.shuffle(choices);
		this.setCorrectIndex();
	}
	
	private void setCorrectIndex(){
		this.correctIndex = choices.indexOf(correct);
	}
	
	public boolean isCorrect(String userChoice) {
		try {
			Integer tmp = Integer.parseInt(userChoice);
			return isCorrect((int)tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public boolean isCorrect(int userChoice) {
		if (userChoice > 3) {
			return false;
		}		
		
		return userChoice == this.correctIndex;
	}

	public boolean hasCorrect() {
		return isCorrect(getUserAnswered());
	}
	
	public static Question createQuestion(String sentence, String genre, String correct, String choice2,
			String choice3, String choice4) throws NoSuchAlgorithmException {
		Question question = new Question(sentence, genre, correct, choice2, choice3, choice4);
		return question;
	}

//	public static Question createQuestion(String sentence, String genre, String correct, String choice2,
//			String choice3, String choice4, Blob imagec, Blob image2, Blob image3, Blob image4) {
//		Question question = new Question(sentence, genre, correct, choice2, choice3, choice4, 
//				imagec, image2, image3, image4);
//		return question;
//	}
	
	public String getToken() {
		return this.token;
	}
	
	private void setToken() throws NoSuchAlgorithmException {
//		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//		byte[] randomBytes = new byte[32];
//		random.nextBytes(randomBytes);
//		this.token = new String(randomBytes, Charset.forName("UTF-8"));
		UUID uuid = UUID.randomUUID();
        this.token = uuid.toString();
	}
		
	@Override
	public String toString() {
		return sentence;
	}
	
}
