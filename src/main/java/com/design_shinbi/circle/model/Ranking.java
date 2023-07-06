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
	
	public Quiz sameIdQuiz(int userId) {
		for (Quiz quiz : scores) {
			if (quiz.getUserId() == userId) {
				return quiz;
			}
		}
		return null;
	}
		
	public synchronized void insertScore(Quiz quiz) throws SQLException {
		//もしquizのステータスがfinishでないなら早期return
		if (!quiz.getState().equals("finish")) {
			System.out.println("終わっていないクイズでランキングに参加しようとしました。");
			return;
		}
		
		//とりあえずソートしておく。
		//this.sort();

		//二部探索でquizを挿入する位置を求める
		int rank = Collections.binarySearch(scores, quiz);
		rank = Math.abs(rank);
		rank -= 1;
		
		//もしランキング外だったら早期return
		if (rank >= Const.RANKING_SIZE_MAX) {
			return;
		}
		
		//同じユーザーがいなかったらそのまま挿入
		//同じユーザーがいた場合、古いレコードの方がスコアが低かったら挿入して、低い方を削除する。
		//例外吐いたらinitして同期をとる
		Quiz oldScore = sameIdQuiz(quiz.getUserId());
		if (oldScore == null) {
			try {
				this.scores.add(rank, quiz);
				int generatedId = this.rankingDao.insertRecord(quiz);
				quiz.setId(generatedId);
				if (scores.size() > Const.RANKING_SIZE_MAX) {
					this.rankingDao.deleteRecord( scores.remove(scores.size() - 1).getId() );
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				this.init();
			}
		} else {
			try {
				if (oldScore.calcScore() < quiz.calcScore() ) {
					this.scores.add(rank, quiz);
					int generatedId = this.rankingDao.insertRecord(quiz);
					quiz.setId(generatedId);
					scores.remove(oldScore);
					this.rankingDao.deleteRecord( oldScore.getId() );
				}				
			} catch (Exception e) {
				e.printStackTrace();
				this.init();
			}
		}

	}

}
