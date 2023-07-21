package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.design_shinbi.circle.model.dao.QuizDAO;
import com.design_shinbi.circle.model.entity.User;

public class Playlog {
	private QuizDAO dao;
	private List<HashMap<String, String>> logs;
	private User user;
	private int limit;
	
	private int LIMIT_DEFAULT = 5;
	
	public Playlog(QuizDAO dao, User user) throws SQLException {
		this.dao = dao;
		this.user = user;
		this.limit = LIMIT_DEFAULT;
		init();
	}
	
	private void init() throws SQLException {
		this.logs = dao.getPlayLog(user, limit);
	}
	
	public List<HashMap<String, String>> getPlaylog(){
		return this.logs;
	}
}
