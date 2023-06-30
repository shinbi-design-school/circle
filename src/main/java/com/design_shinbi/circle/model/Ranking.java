package com.design_shinbi.circle.model;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.design_shinbi.circle.model.dao.RankingDAO;

public class Ranking {
	private List<Quiz> scores;
	private RankingDAO dao;
	
	public Ranking(RankingDAO dao){
		
	}
	
	public void init() throws SQLException {
		this.scores = dao.getRecords();
	}
	
	public void sort() {
		Collections.sort(scores);
	}
	
	private void にぶたん() {
		
	}
}
