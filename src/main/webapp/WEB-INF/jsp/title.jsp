<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.entity.User" %>
<%@ page import="com.design_shinbi.circle.model.Const" %>

<%
	User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
%>

<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/title.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script defer src="js/title.js"></script>
  <title>Circle</title>

</head>

<body>
  <div class="title">
    <div class="intro-mv mv-hidden">
      <video autoplay muted src="movie/intro.mp4"></video>
    </div>
    <div class="title-mv">
      <video autoplay muted loop src="movie/title.mp4"></video>
    </div>
    <button class="start-button" onclick="">
      <img src="images/start-button.png" alt="スタートボタン" class="start-button-link">
    </button>

<%
	if (user != null) {
%>
    <div class="mypage-link-container">
	    <div class="mypage-link">
		    <a href="./user">Home
		    </a>
	    </div>
    </div>
<%
	}
%>
  </div>
</body>

</html>