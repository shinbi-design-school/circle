package com.design_shinbi.circle.servlet;

import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.design_shinbi.circle.model.Ranking;
import com.design_shinbi.circle.model.dao.RankingDAO;
import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.util.DbUtil;

public class BaseServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = this.getServletContext();
		Ranking ranking = (Ranking)application.getAttribute("ranking");
		if (ranking == null) {	
			try {
				Connection connection = DbUtil.connect();
				RankingDAO rankingDao = new RankingDAO(connection);
				UserDAO userDao = new UserDAO(connection);
				ranking = new Ranking(rankingDao, userDao);
				application.setAttribute("ranking", ranking);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
