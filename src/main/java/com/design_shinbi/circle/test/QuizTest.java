package com.design_shinbi.circle.test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.design_shinbi.circle.model.Question;
import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.dao.QuizDAO;
import com.design_shinbi.circle.util.DbUtil;

class QuizTest {

	@Test
	void test() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
		Connection connection = DbUtil.connect();
		QuizDAO dao = new QuizDAO(connection);
		Quiz testQuiz = new Quiz(dao);
		testQuiz.init();
		Question question = testQuiz.pickQuestion();
		System.out.println(question);
	}

}
