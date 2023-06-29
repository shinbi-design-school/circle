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

import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.dao.QuizDAO;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("quiz");
		
		try (Connection connection = DbUtil.connect()){
			QuizDAO dao = new QuizDAO(connection);
			Quiz quiz = new Quiz(dao);	
			connection.close();
			
			session.setAttribute("quiz", quiz);
			
			quiz.setState("playing");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/play");
		dispatcher.forward(req, resp);
	}
	
}
