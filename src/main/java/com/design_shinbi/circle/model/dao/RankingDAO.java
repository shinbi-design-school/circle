package com.design_shinbi.circle.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.circle.model.entity.Score;

public class RankingDAO {
	protected Connection connection;
	private final int HIGHER = 100;
	
	public RankingDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		this.connection = connection;
	}
	
	public List<Score> getHigher() throws SQLException {
		String sql = "select users.name, result.score, result.created_at from result inner join users LIMIT ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, HIGHER);
		ResultSet resultSet = statement.executeQuery();
		
		List<Score> scores = new ArrayList<>();
		
		while(resultSet.next()) {
			Score score = new Score();
			score.setUserId(resultSet.getInt("user_id"));
			score.setTime(resultSet.getLong("time"));
			score.setCorrectCount(resultSet.getInt("correctValue"));
			score.setQuestionValue(resultSet.getInt("questionValue"));
			score.setTimestamp(resultSet.getTimestamp("timestamp"));
			
			scores.add(score);
		}
		
		resultSet.close();
		statement.close();
		
		return scores;
	}
	
	public void insertScore(Score score) {
		
	}
	
	public void にぶたん() {
		
	}
}
