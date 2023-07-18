package com.design_shinbi.circle.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.Question;
import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.entity.User;

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
	public List<Question> choiceQuestions() throws SQLException, NoSuchAlgorithmException {
		
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
	
	public void insertPlayLog(Quiz quiz) throws SQLException {

		String sql = "INSERT INTO `result` (user_id, score, created_at) VALUES (?, ?, ?)";
		PreparedStatement statement = this.connection.prepareStatement(sql);
		
		statement.setInt(1, quiz.getUserId());
		statement.setInt(2, quiz.calcScore());
		statement.setTimestamp(3, Timestamp.valueOf(quiz.getFinishTime()));
		
		statement.executeUpdate();
		
		statement.close();
	}
	
	public List<HashMap<String, String>> getPlayLog(User user, int limit) throws SQLException {
		return getPlayLog(user.getId(), limit);
	}
	
	public List<HashMap<String, String>> getPlayLog(int userId, int limit) throws SQLException {
		
		String sql = "SELECT score, created_at FROM result WHERE user_id = ? ORDER BY created_at LIMIT ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, userId);
		statement.setInt(2, limit);
		
		ResultSet rs = statement.executeQuery();
		List<HashMap<String,String>> result = new ArrayList<>();
		
		while (rs.next()) {
			String score = String.valueOf(rs.getInt("score"));
			LocalDateTime timestamp = rs.getTimestamp("created_at").toLocalDateTime();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("y年M月d日 (E) H時m分s秒");
			String timestampStr = dtf.format(timestamp);

			HashMap<String,String> row = new HashMap<String,String>(){
				{
				put("score", score);
				put("timestamp", timestampStr);
				}				
			};
			result.add(row);
		}

		return result;
	}
}
