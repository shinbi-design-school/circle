<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.design_shinbi.circle.model.Ranking" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="com.design_shinbi.circle.model.Quiz" %>

<%
	Ranking ranking = (Ranking)application.getAttribute("ranking");
	List<Quiz> scores = ranking.getScores();
	//ランキングの表示数。100まで。それ以上はRankingクラスも変更すること。
	int view = 5;
%>

<!DOCTYPE html>
<html lang="ja">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/standings.css">
  <link rel="stylesheet" type="text/css" href="css/header.css">
  <link rel="stylesheet" type="text/css" href="css/transition.css">
  <title>プレイヤーランキング</title>
</head>

<body>
<jsp:include page="header.jsp" />
  <section class="standings">
    <div class="standings__inner">
      <h1 class="standings__title">circle standings</h1>
      <div class="standings__table">
        <div class="standings__row standings__row--head">
          <p class="standings__head standings__head--rank">rank</p>
          <p class="standings__head standings__head--name">name</p>
          <p class="standings__head standings__head--point">point</p>
        </div>
<%
	for (int i = 0; i < view; i++){
%>
        <div class="standings__row">
          <p class="standings__rank standings__rank--<%=i + 1 %>"><%=i + 1 %></p>
          <img class="mypage_icon" src="icon?id=<%=scores.get(i).getUserId() %>">
          <p class="standings__name"><%=scores.get(i).getUserName() %></p>
          <p class="standings__point"><%=scores.get(i).calcScore() %></p>
        </div>
<%
	}
%>        
      </div>
    </div>
  </section>

<jsp:include page="transition.jsp" />

</body>

</html>