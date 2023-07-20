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
import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.model.entity.User;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet(urlPatterns = {"/login", "/"})
public class LoginServlet extends BaseServlet{
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		//ログインしているならトップ画面に飛ばす
		if (loginUser != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/top");
			dispatcher.forward(req, resp);
			return;
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		//ログインしているならトップ画面に飛ばす
		if (loginUser != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/top");
			dispatcher.forward(req, resp);
			return;
		}
		
		try {
			Connection connection = DbUtil.connect();
			String jsp = null;
			String error = "";
			User user = null;
			
			UserDAO dao = new UserDAO(connection);
			
			String email = req.getParameter("email");
			if(email == null || email.isEmpty()) {
				error = "メールアドレスを入力してください。<br>";
			}
			
			String password = req.getParameter("password");
			if(password == null || password.isEmpty()) {
				error = error + "パスワードを入力してください。<br>";
			}
			
			if (error.isEmpty()) {
				user = dao.login(email, password);
			}
			
			if (user == null) {
				if (error.isEmpty()) {
					error = "メールアドレスかパスワードが間違っています。<br>";
				}
				req.setAttribute("error", error);
				jsp = "/WEB-INF/jsp/login.jsp";
			} else {
				session.setAttribute(Const.LOGIN_USER_KEY, user);
				jsp = "/WEB-INF/jsp/title.jsp";
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
