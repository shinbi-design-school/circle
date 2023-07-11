<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	if (name != null){
		name = "value=\"" + name + "\"";
	}
	if (email != null){
		email = "value=\"" + email + "\"";
	}
	
	String error = (String)request.getAttribute("error");
 %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/join.css">
	<script src="./js/analyzer_animation.js"></script>
	<title>アカウント作成</title>
</head>
<body>
	<main>
		<div class="image">
			<canvas class="analyzer"></canvas>
		</div>
		<div class="info">
			<h1>Circle</h1>
			<h2>新規登録</h2>
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
				<form action="join" method="POST">
					<table>
						<tr>
						    <th>ハンドルネーム</th>
						    <td><input type="text" name="name" maxvalue="15" <%=name %> required></td>
						</tr>
						<tr>
						    <th>メールアドレス</th>
						    <td><input type="email" name="email" <%=email %> required></td>
						</tr>
						<tr>
						    <th>パスワード</th>
						    <td><input type="password" name="password" required></td>
						</tr>
						<tr>
						    <th>パスワード確認</th>
						    <td><input type="password" name="confirmed" required></td>
						</tr>
					</table>
						<input type="submit" value="確定" class="submit_button">
						
				</form>
			</div>
		</div>
	</main>
</body>
</html>