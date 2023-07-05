package com.design_shinbi.circle.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.design_shinbi.circle.model.Quiz;


/*
 * ERMasterだとtimestamp型でミリ秒を扱うことができないので、エクスポートしてからtimestampをtimestamp(3)に変更すること。
 */
public class RankingDAO {
	protected Connection connection;
	
	public RankingDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		this.connection = connection;
	}
	
	public List<Quiz> getRecords() throws SQLException {
		String sql = "select ranking.id, ranking.user_id, ranking.created_at, ranking.time, ranking.correctValue,"
				+ " ranking.questionValue, users.name from ranking inner join users on users.id = ranking.user_id";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		List<Quiz> scores = new ArrayList<>();
		
		while(resultSet.next()) {
			Quiz quiz = new Quiz();
			quiz.setId(resultSet.getInt("id"));
			quiz.setUserId(resultSet.getInt("user_id"));
			quiz.setFinishTime(resultSet.getTimestamp("created_at").toLocalDateTime());
			Duration tmp = Duration.ofMillis(resultSet.getLong("time"));
			quiz.setStartTime(quiz.getFinishTime().minus(tmp));
			quiz.setCorrectCount(resultSet.getInt("correctValue"));
			quiz.setQuestionsValue(resultSet.getInt("questionValue"));
			quiz.setUserName(resultSet.getString("name"));
			
			scores.add(quiz);
		}
		
		resultSet.close();
		statement.close();
		
		return scores;
	}
	
	public int insertRecord(Quiz quiz) throws SQLException {
		if(this.connection.isClosed()){
			System.out.println("データベースとの接続が切れています");
		}

		int generatedId = 0;
		String sql = "INSERT INTO ranking (`user_id`, `correctValue`, `questionValue`, `time`, `created_at`) VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement statement = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setInt(1, quiz.getUserId());
		statement.setInt(2, quiz.getCorrectCount());
		statement.setInt(3, quiz.getQuestionsValue());
		statement.setLong(4, Duration.between( quiz.getStartTime(), quiz.getFinishTime() ).toMillis());
		statement.setTimestamp(5, Timestamp.valueOf(quiz.getFinishTime()));
		statement.executeUpdate();
		ResultSet resultSet = statement.getGeneratedKeys();
	
		if (resultSet.next()) {
			generatedId = resultSet.getInt(1);
		}
		
		resultSet.close();
		statement.close();
		
		return generatedId;
		
	}
	
	public void deleteRecord(int index) throws SQLException {
		String sql = "DELETE FROM ranking where id = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, index);
		statement.executeUpdate();

		statement.close();
	}

}
