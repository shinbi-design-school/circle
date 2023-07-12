package com.design_shinbi.circle.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.design_shinbi.circle.model.dao.UserDAO;
import com.design_shinbi.circle.model.entity.Icon;
import com.design_shinbi.circle.util.DbUtil;

@WebServlet("/icon")
public class IconServlet extends BaseServlet {
	@Override
	protected void doGet(
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Connection connection = DbUtil.connect();
			String id = req.getParameter("id");

			UserDAO dao = new UserDAO(connection);
			Icon icon = dao.getIcon(Integer.parseInt(id));
			if (icon != null && icon.getStream() != null) {
				String fileName = icon.getFileName();
				InputStream stream = icon.getStream();

				if (fileName.toLowerCase().endsWith(".png")) {
					resp.setContentType("icon/png");
				} else {
					resp.setContentType("icon/png");
				}

				byte[] buffer = new byte[1024];
				int length = 0;

				while ((length = stream.read(buffer)) > 0) {
					resp.getOutputStream().write(buffer, 0, length);
				}
			}
			connection.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}