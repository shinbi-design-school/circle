package com.design_shinbi.circle.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.entity.User;

@WebServlet("/top")
public class TopServlet extends BaseServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
		
		String jsp = null;
//		if(user == null) {
//			jsp = "/WEB-INF/jsp/login.jsp";
//		} else {
//			jsp = "/WEB-INF/jsp/title.jsp";
//		}
		jsp = "/WEB-INF/jsp/title.jsp";
		
		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

