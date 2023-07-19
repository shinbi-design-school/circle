<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.design_shinbi.circle.model.entity.User" %>
<%@ page import="com.design_shinbi.circle.model.Const" %>

<%
	User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
	String iconFileName = user.getIconFileName();
	String error = (String)request.getAttribute("error");
	int id = 0;
	String email = "";
	String name = "";
	if(user != null) {
		id = user.getId();
		email = user.getEmail();
		name = user.getName();
	}
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" href="css/mypage.css">
    <link rel="stylesheet" type="text/css" href="css/header.css">
  	<link rel="stylesheet" type="text/css" href="css/transition.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <title>user</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function() {
	$("#user_icon_file").on("change", function(){
		var file = $(this).prop("files")[0];
			$(".new_icon_file_name").text(file.name);
		});
	});
</script>
<body>
<jsp:include page="header.jsp" />
<div class="mypage">
	<h1 class="mypage_title">INFORMATION</h1>
	    <div class="mypage_information_container">
		    <div class="mypage_icon">
		        <form id="new_icon_form" method="post" action="upload" enctype="multipart/form-data">
		        <img class="mypage_icon_image" src="icon?id=<%= user.getId() %>">
		        <div>
		            <label>
		            	<div class="photo_icon">
		            		<i class="fa-solid fa-camera"> 選択</i>
		            		<input id="user_icon_file" type="file" name="icon_file">
		            		<div class="new_icon_file_name">選択されていません</div>
			       		</div>
			       	</label>
			       	<label>
			       		<div class="change_icon">
				       		<i class="fa-solid fa-arrows-rotate"> アイコンの更新</i>
			            	<input id="submit" type="submit" name="submit" value="更新">
		            	</div>
		            </label>
		        </div>
		    </div>
	        <input type="hidden" name="id" vale="<%= user.getId() %>">
	        </form>
	        
	        <div class="mypage_information_table">
	        <form method="post" action="user">
	            <table>
	                <tr>
	                    <th>NAME：</th>
	                    <td><%= user.getName() %></td>
	                    <td><input type="text" name="name" value="<%= name %>"></td>
	                </tr>
	                <tr>
	                    <th>E-MAIL：</th>
	                    <td><%= user.getEmail() %></td>
	                    <td><input type="email" name="email" value="<%= email %>"></td>
	                </tr>
	                <tr>
	                    <th>PASSWORD：</th>
	                    <td><%= user.getPassword() %></td>
	                    <td><input type="password" name="password"></td>
	                    <td><input type="password" name="confirmed"></td>
	                    <td>
	                </tr>
	                <tr>
	                    <td>
	                        <input type="submit" name="submit">
	                    </td>
	                </tr>
	            </table>
	            <input type="hidden" name="id" value="<%= id %>">
	        </div>
	    </div>
	    <hr>
	    <div class="mypage_container">
	        <div class="mypage_ranking">
	            <h3>ランキング</h3>
	            <p>たかし</p>
	            <p>たかこ</p>
	            <p>たかだ</p>
	        </div>
	    </div>
	</div>
</div>	
</body>
</html>