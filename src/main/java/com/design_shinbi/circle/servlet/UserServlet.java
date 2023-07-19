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

		return jsp;
	}

}