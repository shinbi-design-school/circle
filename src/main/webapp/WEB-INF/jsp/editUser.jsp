<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.design_shinbi.circle.model.entity.User" %>
<%@ page import="com.design_shinbi.circle.model.Const" %>

<%
	User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
	String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="ja">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" type="text/css" href="css/reset.css">
	    <link rel="stylesheet" type="text/css" href="css/join.css">
	  	<script src="./js/analyzer_animation.js"></script>
	    <title>editUser</title>
	</head>
	<body>
		<main>
			<div class="image">
				<canvas class="analyzer"></canvas>
			</div>
			<div class="info">
				<h1>Circle</h1>
				<h2>ユーザー情報の編集</h2>
				<div class="error_container">
			<%
				if (error != null){
			%>
				<h3 class="error-message"><%=error %></h3>
			<%		
				}
			%>
			</div>
			<div class="form_container">
			<form method="post" action="user">
				<table>
					<tr>
						<th>NAME:</th>
						<td>
							<input type="text" name="name" value="<%= user.getName() %>">
						</td>
					</tr>
					<tr>
						<th>E-MAIL:</th>
						<td>
							<input type="email" name="email" value="<%= user.getEmail() %>">
						</td>
					</tr>
					<tr>
						<th>PASSWORD:</th>
						<td>
							<input type="password" name="password">
						</td>
					</tr>
					<tr>
						<th>PASSWORD（確認用）:</th>
						<td>
							<input type="password" name="confirmed">
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="更新" class="submit_button">
						</td>
					</tr>
				</table>
				<input type="hidden" name="id" value="<%= user.getId() %>">
				<input type="hidden" name="operation" value="update">
			</form>
<%
	if(error != null) {
%>
		<div id="error">
			<%= error %>
		</div>
<%
	}
%>			</div>
		</div>
	</main>
</body>
</html>	
			