package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.design_shinbi.circle.model.dao.RankingDAO;


/*
 * アプリケーションスコープで扱うこと
 * 排他制御も使うこと
 */
public class Ranking {
	private List<Quiz> scores;
	private RankingDAO dao;
	
	public Ranking(RankingDAO dao) throws SQLException{
		this.init();
	}
	
	public void init() throws SQLException {
		this.scores = dao.getRecords();
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
}
