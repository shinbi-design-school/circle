package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

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

@WebServlet("/user")
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(
			HttpServletRequest request,
			HttpServletResponse reaponse) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Const.LOGIN_USER_KEY);

		String jsp = null;
		jsp = "/WEB-INF/jsp/mypage.jsp";
		if (loginUser == null) {
			jsp = "/WEB-INF/jsp/login.jsp";
		} else {
			try {
				jsp = operate(request, loginUser);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, reaponse);
	}

	private String operate(HttpServletRequest request, User loginUser)
			throws Exception {
		String jsp = null;
		Connection connection = DbUtil.connect();
		String operation = request.getParameter("operation");
		UserDAO dao = new UserDAO(connection);
		if (operation != null) {
			if (operation.equals("edit")) {
				jsp = editUser(request, dao);
			} else if (operation.equals("update")) {
				jsp = updateUser(request, dao);
			}
		}

		if (jsp == null) {
			jsp = "/WEB-INF/jsp/editUser.jsp";
		}

		return jsp;
	}

	private String editUser(HttpServletRequest request, UserDAO dao)
			throws NumberFormatException, SQLException {
		String id = request.getParameter("id");
		User user = dao.findById(Integer.parseInt(id));
		request.setAttribute("user", user);
		String jsp = "/WEB-INF/jsp/mypage.jsp";

		return jsp;
	}

	private String updateUser(HttpServletRequest request, UserDAO dao)
			throws SQLException, NoSuchAlgorithmException {
		String jsp = null;
		String error = "";

		int id = Integer.parseInt(request.getParameter("id"));

		String name = request.getParameter("name");
		if (name == null || name.isEmpty()) {
			error += "名前を入力してください。";
		}

		String email = request.getParameter("email");
		String is_admin = request.getParameter("is_admin");
		String password = request.getParameter("password");
		String confirmed = request.getParameter("confirmed");

		if (!password.equals(confirmed)) {
			error += "パスワードが一致しません。";
		}

		if (error.isEmpty()) {
			dao.updateUser(id, name, Boolean.parseBoolean(is_admin));
			if (!password.isEmpty()) {
				dao.updatePassword(id, password);
			}
			if (!name.isEmpty()) {
				dao.updateName(id, name);
			}
			if (!email.isEmpty()) {
				dao.updateEmail(id, email);
			}
		} else {
			User user = dao.findById(id);
			request.setAttribute("user", user);
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/mypage.jsp";
		}

		return jsp;
	}
}