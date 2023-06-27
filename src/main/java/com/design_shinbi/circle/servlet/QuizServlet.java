package com.design_shinbi.circle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Quiz;

@WebServlet("/play")
public class QuizServlet extends HttpServlet{
	private Quiz quiz;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsp = null;
		HttpSession session = req.getSession();
		
		
	}
	
}
