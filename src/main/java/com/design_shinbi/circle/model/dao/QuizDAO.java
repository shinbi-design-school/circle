package com.design_shinbi.circle.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.Question;

public class QuizDAO {
	protected Connection connection;
	
	public QuizDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		this.connection = connection;
	}
	
	/*
	 * データベースからConst.QUIZ_CHOICE_VALUEの数だけクイズ情報を取得
	 * sql文のRAND()はレコードが増えると低速になる
	 * 多分変えることないけど
	 */
	public List<Question> choiceQuestions() throws SQLException {
		
		String sql = "SELECT * FROM `questions` ORDER BY RAND() LIMIT ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, Const.QUIZ_CHOICE_VALUE);
		ResultSet resultSet = statement.executeQuery();
		
		List<Question> questions = new ArrayList<>();
		
		while(resultSet.next()) {
			String sentence = resultSet.getString("sentence");
			String genre = resultSet.getString("genre");
			String correct = resultSet.getString("correct");
			String choice2 = resultSet.getString("choice2");
			String choice3 = resultSet.getString("choice3");
			String choice4 = resultSet.getString("choice4");
//			Blob imagec = resultSet.getBlob("imagac");
//			Blob image2 = resultSet.getBlob("image2");
//			Blob image3 = resultSet.getBlob("imaga3");
//			Blob image4 = resultSet.getBlob("imaga4");
//			questions.add(Question.createQuestion(sentence, genre, correct, choice2, choice3, choice4, imagec, image2, image3, image4));
			questions.add(Question.createQuestion(sentence, genre, correct, choice2, choice3, choice4));
		}
		
		resultSet.close();
		statement.close();
		
		return questions;
	}
}
