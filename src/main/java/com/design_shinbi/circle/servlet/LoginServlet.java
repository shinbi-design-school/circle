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
import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.model.entity.User;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Connection connection = DbUtil.connect();
			String jsp = null;
			String error = "";
			User user = null;
			
			UserDAO dao = new UserDAO(connection);
			
			String email = req.getParameter("email");
			if(email == null || email.isEmpty()) {
				error = "メールアドレスを入力してください。";
			}
			
			String password = req.getParameter("password");
			if(password == null || password.isEmpty()) {
				error = error + "パスワードを入力してください。";
			}
			
			if (error.isEmpty()) {
				user = dao.login(email, password);
			}
			
			if (user == null) {
				if (error.isEmpty()) {
					error = "メールアドレスかパスワードが間違っています。";
				}
				req.setAttribute("error", error);
				jsp = "/WEB-INF/jsp/login.jsp";
			} else {
				HttpSession session = req.getSession();
				session.setAttribute(Const.LOGIN_USER_KEY, user);
				jsp = "/WEB-INF/jsp/loginTest.jsp";
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
