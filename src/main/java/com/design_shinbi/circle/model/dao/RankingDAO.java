package com.design_shinbi.circle.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.circle.model.Quiz;

public class RankingDAO {
	protected Connection connection;
	private final int HIGHER = 100;
	
	public RankingDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		this.connection = connection;
	}
	
	public List<Quiz> getRecords() throws SQLException {
		String sql = "select users.name, result.score, result.created_at from result inner join users LIMIT ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, HIGHER);
		ResultSet resultSet = statement.executeQuery();
		
		List<Quiz> scores = new ArrayList<>();
		
		while(resultSet.next()) {
			Quiz quiz = new Quiz();
			quiz.setId(resultSet.getInt("id"));
			quiz.setUserId(resultSet.getInt("user_id"));
			quiz.setFinishTime(resultSet.getTimestamp("timestamp").toLocalDateTime());
			Duration tmp = Duration.ofMillis(resultSet.getLong("time"));
			quiz.setStartTime(quiz.getFinishTime().minus(tmp));
			quiz.setCorrectCount(resultSet.getInt("correctValue"));
			quiz.setQuestionsValue(resultSet.getInt("questionValue"));
			
			scores.add(quiz);
		}
		
		resultSet.close();
		statement.close();
		
		return scores;
	}
	
	//排他制御要る？
	public void insertRecord(Quiz quiz) {
		
	}
	
	//排他制御要る？
	private void deleteRecord() {
		
	}
}
