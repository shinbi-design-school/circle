<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Ranking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.design_shinbi.circle.Quiz" %>

<%
	Ranking ranking = (Ranking)application.getAttribute("ranking");
	List<Quiz> scores = ranking.getScores();
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
	for (Quiz quiz : scores){
		//データベースのアクセスが増えすぎるので訂正必須
		String name = Ranking.userDao().findUserNameById(quiz.getUserId());
%>
		<tr>
			<th><%=name %></th>
			<td><%=quiz.calcScore() %></td>
		</tr>
<%
	}
%>
	</table>
</body>
</html>