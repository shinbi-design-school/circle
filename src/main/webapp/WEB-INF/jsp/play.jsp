<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Question" %>

<%
	Question question = (Question)session.getAttibute("question");
%>
<!DOCTYPE html>
<html>
<body>
	<form method="POST" action="/play">
		<div>
			<input id="choice" type="radio" name="userChoice" value="<%= %>">
			
		</div>
		<div>
			<input id="choice" type="radio" name="userChoice" value="<%= %>">
			
		</div>
		<div>
			<input id="choice" type="radio" name="userChoice" value="<%= %>">
			
		</div>
		<div>
			<input id="choice" type="radio" name="userChoice" value="<%= %>">
			
		</div>
</body>
</html>
<%
	