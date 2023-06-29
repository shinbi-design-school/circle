<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Question" %>
<%@ page import="java.util.List" %>

<%
	Question question = (Question)session.getAttribute("question");
	List<String> choices = question.getChoices();
%>
<!DOCTYPE html>
<html>
<body>
    <div id="wrapper">
        <main class="quiz">
            <div class="inner-block">
                <h1 class="quiz-question-number">問題1</h1>
                <h2 class="quiz-question">問題文はここに表示します。問題文はここに表示します。問題文はここに表示します。問題文はここに表示します。</h2>
                <ul class="quiz-answer">
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="<%= choices.get(0) %>">
                        </label>
                    </li>
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="<%= choices.get(1) %>">
                        </label>
                    </li>
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="<%= choices.get(2) %>">
                        </label>
                    </li>
                    <li>
                        <label class="quiz-button">
                            <input name="userChoice" type="radio" value="<%= choices.get(3) %>">
                        </label>
                    </li>
                    <input class="quiz-answer-button" type="submit" value="回答">
                </ul>
            </div>
        </main>
    </div>
</body>
</html>
	