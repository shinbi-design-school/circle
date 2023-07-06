package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.Ranking;

@WebServlet("/advance")
public class QuizServletAdvance extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Quiz quiz = (Quiz)session.getAttribute("quiz");
		quiz.chkState();

		resp.setContentType("text/plain");
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        String choice = req.getParameter("userChoice");
		String token = req.getParameter("token");
		
		PrintWriter out = resp.getWriter();

		if (choice == null || token == null) {
			out.println("POSTされたデータに不備があります。");
			return;
		}
		
		quiz.pick().setUserAnswered(choice);
		
		if (quiz.pick().hasCorrect() && quiz.pick().getToken().equals(token)) {
			out.println("correct");
			quiz.setCorrectCount(quiz.getCorrectCount() + 1);
		} else {
			out.println("incorrect");
		}
		quiz.setAnswered(quiz.getAnswered() + 1);
		
		if (quiz.getAnswered() >= quiz.getQuestionsValue()) {
			quiz.setState("finish");
			out.println("finish");
			try {
				resultProcess(quiz);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
						
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Quiz quiz = (Quiz)session.getAttribute("quiz");
		
		resp.setContentType("text/plain");
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
	        
        PrintWriter out = resp.getWriter();
        if (quiz.pick() != null) {
        	out.println(quiz.pick().getSentence());
        	for (String choice : quiz.pick().getChoices()) {        	
        		out.println(choice);
        	}
        	out.println(quiz.pick().getToken());        	
        }
	}	
	
	/* クイズゲーム終了時に、ランキングやユーザー情報を変更する処理 */
	private void resultProcess(Quiz quiz) throws SQLException {
		quiz.finish();
		ServletContext application = this.getServletContext();
		Ranking ranking = (Ranking)application.getAttribute("ranking");
		ranking.insertScore(quiz);
	}
}
