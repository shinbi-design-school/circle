<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String status = (String)request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/header.css">
  <link rel="stylesheet" type="text/css" href="css/404.css">

  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script defer src="js/script.js"></script>
  <title>エラー</title>
</head>
<body>
	<jsp:include page="header.jsp" />
<%
	if (status.equals("404")) {
%>
  <div class="error">
    <img src="./images/404.png" alt="エラー画面">
  </div>
<%
	} else {
%>
	<p><%=status %></p>
<%
	}
%>
</body>
</html>