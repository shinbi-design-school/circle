<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp" />
	<title>ログイン</title>
</head>
<body>
	<main>
		<form action="login" method="POST">
			<table id="login">
				<tr>
					<th>メールアドレス</th>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="ログイン" id="submit"</td>
				</tr>
			</table>
		</form>
<%
	if(error != null){
%>
		<div id="error"><%=error %></div>
<%
	}
%>
	</main>
</body>
</html>