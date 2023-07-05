<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Question" %>
<%@ page import="com.design_shinbi.circle.model.Quiz" %>
<%@ page import="java.util.List" %>

<%
	Quiz quiz = (Quiz)session.getAttribute("quiz");
	Question question = quiz.pick();
	List<String> choices = question.getChoices();
	int qNum = question.getUserAnswered() == -1 ? quiz.getAnswered() + 1 : quiz.getAnswered();
%>
<!DOCTYPE html>
<html>
<body>
    <div id="wrapper">
        <main class="quiz">
            <div class="inner-block">
                <h1 class="quiz-question-number">問題<%=qNum %></h1>
                <h2 class="quiz-question"><%=question.getSentence() %></h2>
<%
	if (question.getUserAnswered() == -1){
%>
                <form action="advance" method="POST">
                <input name="token" type="hidden" value="<%=question.getToken() %>">
                <ul class="quiz-answer">
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="0">
                            <span><%= choices.get(0) %></span>
                        </label>
                    </li>
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="1">
                            <span><%= choices.get(1) %></span>
                        </label>
                    </li>
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="2">
                            <span><%= choices.get(2) %></span>
                        </label>
                    </li>
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="3">
                            <span><%= choices.get(3) %></span>
                        </label>
                    </li>
                    <input class="quiz-answer-button" type="submit" value="回答">
                </ul>
                </form>
<%
	}
%>
            </div>
<%
if (question.getUserAnswered() != -1){
		if (question.isCorrect(question.getUserAnswered())){
%>
	<h2>正解です</h2>
	<a href="play">次へ</a>
<%
		} else {
%>
	<h2>不正解です</h2>
	<a href="play">次へ</a>
<%
		}
	}
%>
        </main>
    </div>
</body>
</html>
	