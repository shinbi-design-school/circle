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

@WebServlet("/join")
public class AddUserServlet extends HttpServlet{
	
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
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/join.jsp");
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
		
		String error = "";
		try (Connection connection = DbUtil.connect()){
			
			UserDAO dao = new UserDAO(connection);
	
			String email = req.getParameter("email");
			if (email == null || email.isEmpty()) {
				error = "メールアドレスを入力してください。<br>";
			} else {
				User user = dao.findByEmail(email);
				if (user != null) {
					error = "そのメールアドレスは既に使われています。<br>";
				}
			}
			
			String name = req.getParameter("name");
			if (name == null || name.isEmpty()) {
				error += "名前を入力してください。<br>";
			}
			
			String password = req.getParameter("password");
			if (password == null || password.isEmpty()) {
				error += "パスワードを入力してください。<br>";
			}

			String confirmed = req.getParameter("confirmed");
			if (!password.equals(confirmed)) {
				error += "パスワードが一致しません。<br>";
			}

			if (!error.isEmpty()) {
				req.setAttribute("error", error);
			} else {
				dao.addUser(email, name, password, false);
				User user = dao.login(email, password);
				session.setAttribute(Const.LOGIN_USER_KEY, user);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/title.jsp");
				dispatcher.forward(req, resp);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			error += "システムエラー<br>";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/join.jsp");
		dispatcher.forward(req, resp);
	}
}
