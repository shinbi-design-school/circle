package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.design_shinbi.circle.model.dao.RankingDAO;
import com.design_shinbi.circle.model.dao.UserDAO;


/*
 * アプリケーションスコープで扱うこと
 * 排他制御も使うこと
 */
public class Ranking {
	private List<Quiz> scores;
	private RankingDAO rankingDao;
	private UserDAO userDao;
	
	public Ranking(RankingDAO rankingDao, UserDAO userDao) throws SQLException{
		this.init();
	}
	
	public void init() throws SQLException {
		this.scores = rankingDao.getRecords();
		this.sort();
	}
	
	public void sort() {
		Collections.sort(scores);
	}
	
	private void にぶたん() {
		
	}

	public List<Quiz> getScores() {
		return scores;
	}
	
	public UserDAO userDao() {
		return userDao;
	}

}
