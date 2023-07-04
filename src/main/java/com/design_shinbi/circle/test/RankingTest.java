package com.design_shinbi.circle.test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.Ranking;
import com.design_shinbi.circle.model.dao.RankingDAO;
import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.util.DbUtil;

class RankingTest {

	@Test
	void test() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
		Connection connection = DbUtil.connect();
		RankingDAO rankingDao = new RankingDAO(connection);
		UserDAO userDao = new UserDAO(connection);
		Ranking ranking = new Ranking(rankingDao, userDao);

		Quiz quiz = new Quiz();
		quiz.setId(0);
		quiz.setUserId(0);
		quiz.setFinishTime(LocalDateTime.now());
		quiz.setStartTime(quiz.getFinishTime().minusMinutes(1L));
		quiz.setCorrectCount(5);
		quiz.setQuestionsValue(5);
		
		for (Quiz q : ranking.getScores()) {
			System.out.printf("%s: %d点\n",q.getUserName(), q.calcScore());
		}

		ranking.insertScore(quiz);
		
	}

}
