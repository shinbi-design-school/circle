package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.model.entity.User;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/upload")
@MultipartConfig
public class UploadIconServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse reaponse) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute(Const.LOGIN_USER_KEY);

		String jsp = null;

		if (loginUser == null) {
			jsp = "/WEB-INF/jsp/login.jsp";
		} else {
			try {
				Connection connection = DbUtil.connect();
				UserDAO dao = new UserDAO(connection);

				try {

				} catch (Exception e) {

					this.setIcon(request, dao, loginUser);
				}

				request.setAttribute("id", loginUser);

				jsp = "/WEB-INF/jsp/mypage.jsp";

				connection.close();
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
		dispatcher.forward(request, reaponse);
	}

	private void setIcon(HttpServletRequest req, UserDAO dao, User entity)
			throws Exception {
		Part part = req.getPart("icon_file");
		String fileName = part.getSubmittedFileName();
		boolean deleteFlag = Boolean.parseBoolean(req.getParameter("delete_icon_flag"));

		if (fileName == null || fileName.isEmpty()) {
			if (deleteFlag) {
				dao.removeIcon(entity.getId());
			}
		} else {
			dao.setIcon(entity.getId(), fileName, part.getInputStream());
		}

	}
}