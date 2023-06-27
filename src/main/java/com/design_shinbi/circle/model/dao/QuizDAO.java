package com.design_shinbi.circle.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class QuizDAO {
	protected Connection connection;
	
	public UserDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		this.connection = connection;
		this.initialize();
	}
	
	private void initialize() {
		
	}
	
	private choiceQuestion() {
		
	}
}
