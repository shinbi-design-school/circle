<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Question" %>
<%@ page import="com.design_shinbi.circle.model.Quiz" %>
<%@ page import="java.util.List" %>

<%
	Quiz quiz = (Quiz)session.getAttribute("quiz");
	Question question = (Question)session.getAttribute("question");
	List<String> choices = question.getChoices();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

async function isCorrect(choice, token){
	const xhr = new XMLHttpRequest();
	xhr.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
	const data = {"choice" : choice, "token" : token};

	let response;
	xhr.onreadystatechange = () => {
		if( this.readyState == 4 && this.status == 200 ){
			alert( this.responseText );
		}
	});
	
	xhr.open("POST", "advance");
	xhr.send( EncodeHTMLForm( data ) );
	
	return false;
}
</script>
</head>
<body>
	<h1 class="quiz-question-number">問題<%=qNum %></h1>
	<h2 class="quiz-question"><%=question.getSentence() %></h2>
	<ul class="quiz-answer">
	   <li>
	       <label class="quiz-button">
	           <input name="userChoice" type="button" value="0">
	           <span><%= choices.get(0) %></span>
	       </label>
	   </li>
	   <li>
	       <label class="quiz-button">
	           <input name="userChoice" type="button" value="1">
	           <span><%= choices.get(1) %></span>
	       </label>
	   </li>
	   <li>
	       <label class="quiz-button">
	           <input name="userChoice" type="button" value="2">
	           <span><%= choices.get(2) %></span>
	       </label>
	   </li>
	   <li>
	       <label class="quiz-button">
	           <input name="userChoice" type="button" value="3">
	           <span><%= choices.get(3) %></span>
	        </label>
	    </li>
	</ul>
</body>
</html>