<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Question" %>
<%@ page import="com.design_shinbi.circle.model.Quiz" %>
<%@ page import="java.util.List" %>

<%
	Quiz quiz = (Quiz)session.getAttribute("quiz");
	Question question = quiz.pick();
	List<String> choices = question.getChoices();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="quiz-question">undefined</div>
	<div>
		<input class="quiz-text01" type="button" value="undefined">
		<input class="quiz-text02" type="button" value="undefined">
		<input class="quiz-text03" type="button" value="undefined">
		<input class="quiz-text04" type="button" value="undefined">
	</div>
	<div class="correct-image"></div>
	<div class="incorrect-image"></div>
<script src="./js/judgement.js"></script>
</body>
</html>
	