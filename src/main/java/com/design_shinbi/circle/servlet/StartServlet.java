package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.Quiz;
import com.design_shinbi.circle.model.dao.QuizDAO;
import com.design_shinbi.circle.model.entity.User;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/start")
public class StartServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("quiz");
		User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		//ログインしているかどうかの分岐処理
		if (loginUser == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/top");
			dispatcher.forward(req, resp);
			return;
		}
		
		try (Connection connection = DbUtil.connect()){
			QuizDAO dao = new QuizDAO(connection);
			Quiz quiz = new Quiz(dao);	
			quiz.setUserId(loginUser.getId());
			quiz.setUserName(loginUser.getName());
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
