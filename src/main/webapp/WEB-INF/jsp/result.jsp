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
<title>Insert title here</title>
</head>
<body>
	<h1>リザルトページ</h1>
	<div class="result">
		<p><%=quiz.getQuestionsValue() %>問中</p>
		<p><%=quiz.getCorrectCount() %>問正解しました。</p>
		<p>正解率は<%=correctRate %>%です</p>
		<p><%=quiz.getElapsedSeconds()%>秒かかりました。</p>
		<p>履歴</p>
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
	
	<a href="./mypage">ユーザーページへ</a>
	<a href="./top">もう一度プレイ</a>
</body>
</html>