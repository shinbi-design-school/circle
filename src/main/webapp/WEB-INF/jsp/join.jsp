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
<title>アカウント作成</title>
</head>
<body>
<%
	if (error != null){
%>
	<h3 class="error-message"><%=error %></h3>
<%		
	}
%>
	<form action="join" method="POST">
		<tr>
		    <th>ニックネーム</th>
		    <td><input type="text" name="name" maxvalue="15" <%=name %>></td>
		</tr>
		<tr>
		    <th>メールアドレス</th>
		    <td><input type="email" name="email" <%=email %>></td>
		</tr>
		<tr>
		    <th>パスワード</th>
		    <td><input type="password" name="password"></td>
		</tr>
		<tr>
		    <th>パスワード確認</th>
		    <td><input type="password" name="confirmed"></td>
		</tr>
		<tr>
		    <td><input type="submit" value="アカウント作成"</td>
		</tr>
	</form>

</body>
</html>