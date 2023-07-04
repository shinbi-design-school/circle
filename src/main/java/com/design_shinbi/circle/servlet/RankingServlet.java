package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.design_shinbi.circle.model.Ranking;
import com.design_shinbi.circle.model.dao.RankingDAO;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = this.getServletContext();
		try (Connection connection = DbUtil.connect()){
			RankingDAO dao = new RankingDAO(connection);
			Ranking ranking = new Ranking(dao);
			application.setAttribute("ranking", ranking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsp = "/WEB-INF/jsp/ranking.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}
	
}
