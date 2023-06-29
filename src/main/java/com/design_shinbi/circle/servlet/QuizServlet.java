package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.dao.QuizDAO;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/play")
public class QuizServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsp = null;
		HttpSession session = req.getSession();
		Quiz quiz = (Quiz)session.getAttribute("quiz");
		if (quiz == null) {
		
			try (Connection connection = DbUtil.connect()){
				QuizDAO dao = new QuizDAO(connection);
				quiz = new Quiz(dao);	
				connection.close();
				
				quiz.setState("standby");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if (quiz != null) {
			if (quiz.getState().equals("standby")) {
				jsp = "/WEB-INF/start.jsp";
			} else if(quiz.getAnswered() < Const.QUIZ_CHOICE_VALUE) {
				jsp = "/WEB-INF/play.jsp";
			} else {
				jsp = "/WEB-INF/result.jsp";
			}
		} else {
			jsp = "/WEB-INF/error.jsp";
		}
				
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}

	
	
}
