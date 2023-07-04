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
		this.rankingDao = rankingDao;
		this.userDao = userDao;
		this.init();
	}
	
	public void init() throws SQLException {
		this.scores = rankingDao.getRecords();
		for (Quiz quiz : this.scores) {
			quiz.setUserName( userDao.findUserNameById(quiz.getUserId()) );
		}
		this.sort();
	}
	
	public void sort() {
		Collections.sort(scores);
	}
	
	public List<Quiz> getScores() {
		return scores;
	}
	
	public UserDAO userDao() {
		return userDao;
	}
	
	public synchronized void insertScore(Quiz quiz) {
		//二部探索でquizを挿入する位置を求める
		//もしランキング外だったら早期return
		//ランキング外でなかったらその位置に挿入する
			//おなじIDが登録されていなかったら最後尾を取り除く
			//おなじIDが登録されていたらスコアが低い方のレコードを取り除く

		System.out.println(quiz.calcScore());
		int i = Collections.binarySearch(scores, quiz);
		System.out.println(i);
	}

}
