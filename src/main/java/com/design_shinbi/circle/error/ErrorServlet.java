package com.design_shinbi.circle.error;

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

@WebServlet("/notfound")
public class ErrorServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute(Const.LOGIN_USER_KEY);

//		String error = (String)req.getAttribute("javax.servlet.error.message");
//		FileNotFoundException error = new FileNotFoundException("指定されたページが存在しません。");
		String error = "404";
		
		String jsp = null;
		
		if (loginUser == null) {
			jsp = "/WEB-INF/jsp/login.jsp";
		} else {
			req.setAttribute("status", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}
	
}
