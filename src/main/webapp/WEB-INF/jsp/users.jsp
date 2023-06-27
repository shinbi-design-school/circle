<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.design_shinbi.circle.model.entity.User" %>
    
<%
	List<User> users = (List<User>)request.getAttribute("users");
	String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
	<title>ユーザー管理</title>
</head>
<body>
	<main>
<%
	if(error != null){
%>
		<div id="error"><%=error %></div>
<%
	}
%>
		<form action="user" id="user_form" method="post">
			<input type="hidden" id="user_id_input" name="id" value="">
			<input type="hidden" id="operation_input" name="operation" value="">
		</form>
		<div>
			<a href="javascript:newUser()">
				<span class="icon fas fa-user-plus"></span>
			</a>
		</div>
		<table id="users">
			<tr>
				<th>アカウント名</th>
				<th>名前</th>
				<th>権限</th>
			</tr>
<%
			for(User user : users){
				String authority = user.isAdmin() ? "管理者" : "一般";
%>
			<tr>
				<td><%=user.getEmail() %></td>
				<td><%=user.getName() %></td>
				<td><%=authority %></td>
			</tr>
<%
			}
%>
		
		</table>

	</main>
</body>
</html>