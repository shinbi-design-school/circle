package com.design_shinbi.circle.test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.design_shinbi.circle.model.dao.QuizDAO;
import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.model.entity.User;
import com.design_shinbi.circle.util.DbUtil;

class PlayLogTest {

	@Test
	void test() {
		try (Connection connection = DbUtil.connect()){
			UserDAO userDao = new UserDAO(connection);
			QuizDAO quizDao = new QuizDAO(connection);
			User testUser = userDao.findById(2);
			
			List<HashMap<String, String>> logs = quizDao.getPlayLog(testUser, 5);
			
			for (HashMap<String, String> row : logs) {
				System.out.printf("スコア：%s　、　日時：%s",row.get("score"), row.get("timestamp"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
