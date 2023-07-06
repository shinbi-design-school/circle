package com.design_shinbi.circle.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.entity.User;

@WebServlet("/play")
public class QuizServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsp = null;
		HttpSession session = req.getSession();
		User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		//ログインしているかどうかの分岐処理
		if (loginUser == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
			dispatcher.forward(req, resp);
			return;
		}
		
		Quiz quiz = (Quiz)session.getAttribute("quiz");
		
		if (quiz != null) {
			quiz.chkState();
			if (quiz.getState().equals("standby")) {
				jsp = "/WEB-INF/jsp/standby.jsp";
				
			} else if(quiz.getState().equals("playing")) {
				jsp = "/WEB-INF/jsp/play.jsp";
//				htmlだとQuizが生成されるまえにjudgement.js経由のgetが行われてぐちゃぐちゃになる
//				jsp = "/WEB-INF/jsp/play.html";
				
			} else if(quiz.getState().equals("finish")) {
				jsp = "/WEB-INF/jsp/result.jsp";
			}
		} else {
			jsp = "/WEB-INF/jsp/standby.jsp";
		}
				
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}


	
}
