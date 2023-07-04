<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Ranking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.design_shinbi.circle.Quiz" %>

<%
	ServletContext application = this.getServletContext();
	Ranking ranking = (Ranking)application.getAttribute("ranking");
	List<Quiz> scores = ranking.getScores();
	int viewValue = 10;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランキング</title>
</head>
<body>
	<table>
<%
	for (int i = 0; i < viewValue; i++)){
%>
		<tr>
			<th><%=scores.get(i).getUserName() %></th>
			<td><%=scores.get(i).calcScore() %></td>
		</tr>
<%
	}
%>
	</table>
</body>
</html>