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
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/play.css">
  <script defer src="js/judgement.js"></script>
  <script type="module" src="js/judgementViewer.js"></script>
  <script type="module" src="js/paricles.js"></script>
  <title>Circle</title>
  <style>



  </style>
</head>
<body>

  <main class="quiz">

    <div class="inner-block">

      <div class="quiz-content">

        <div class="quiz-question-number">
          問題１
        </div>
        <h2 class="quiz-question">
          undefined
        </h2>

        <ul class="quiz-answer">
          <li>
            <label class="quiz-button button01">
              <input type="button" value="" class="quiz-text01" value="undefined">
            </label>
          </li>
          <li>
            <label class="quiz-button button02">
              <input type="button" value="" class="quiz-text02" value="undefined">
            </label>
          </li>
          <li>
            <label class="quiz-button button03">
              <input type="button" value="" class="quiz-text03" value="undefined">
            </label>
          </li>
          <li>
            <label class="quiz-button button04">
              <input type="button" value="" class="quiz-text04" value="undefined">
            </label>
          </li>
        </ul>
      </div>
    </div>
  <canvas class="particles"></canvas>
  <canvas class="judgement-canvas canvas-hidden"></canvas>
  </main>
<%--
  <div class="play-await">
    <img src="./images/question.png" alt="">
  </div>
  <div class="play-start">
    <img src="./images/quiz.png" alt="">
  </div>
  <div class="correct-image judgement-image">
    <img src="./images/maru.png" alt="">
  </div>
  <div class="incorrect-image judgement-image">
    <img src="./images/batu.png" alt="">
  </div>
--%>
</body>

</html>