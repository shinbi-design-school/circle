<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.design_shinbi.circle.model.Quiz" %>
<%@ page import="com.design_shinbi.circle.model.Question" %>
<%@ page import="java.util.List" %>

<%
	Quiz quiz = (Quiz)session.getAttribute("quiz");
	String correctRate = String.format("%.1f", quiz.getCorrectRate() * 100);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/result.css">
  <link rel="stylesheet" type="text/css" href="css/header.css">
  <link rel="stylesheet" type="text/css" href="css/transition.css">
  <script src="./js/stuffed_roll.js"></script>
<title>リザルトページ</title>
</head>
<body>
<jsp:include page="header.jsp" />
	<section class="result">
	
		<div class="result_inner">
			<h1 class="result_title">Your Results</h1>
				<div class="result_body">
					<div class="result_text">
						<p class="result_correct">
							<%=quiz.getQuestionsValue() %>問中
							<%=quiz.getCorrectCount() %>問正解しました。
						</p>
						<p class="result_accuracy">
							正解率は<%=correctRate %>%です
						</p>
						<p class="result_time">
							クリアまで<%=quiz.getElapsedSeconds()%>秒かかりました。
						</p>
					</div>
				</div>
	</section>
	
	<div class="result_table">
		<p class="result_head">プレイ履歴</p>
		<table>
<%
	for (Question question : quiz.getQuestions()){
%>
			<tr>
				<th><%=question.getSentence() %></th>
<% String hasCorrect = question.hasCorrect() ? "○" : "×"; %>
				<td><%=hasCorrect %></td>
			</tr>
<%
	}
%>
		</table>
	</div>
	<div class="ranking">
		<div class="ranking-link">
			<a href="./ranking" title="ランキング">RANKING</a>
		</div>
	</div>
	
	<div class="stuffed-roll">
		<div class="intro-mv">
    		<video autoplay muted loop src="movie/stuffed-roll.mp4" class="stuffed-roll-mv mv-hidden"></video>
  		</div>
	</div>
	
<jsp:include page="transition.jsp" />
</body>
</html>