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
import com.design_shinbi.circle.model.entity.User;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		//ログインしているかどうかの分岐処理
		if (loginUser == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
			dispatcher.forward(req, resp);
			return;
		}
		
		String jsp = "/WEB-INF/jsp/ranking.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}
	
}
