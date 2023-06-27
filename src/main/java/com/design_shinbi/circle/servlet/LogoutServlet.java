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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute(Const.LOGIN_USER_KEY);
		
		String jsp = "/WEB-INF/jsp/login.jsp";
		
		try  {
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
