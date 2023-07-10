<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.design_shinbi.circle.model.entity.User" %>
<%@ page import="com.design_shinbi.circle.model.Const" %>

<%
	User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
%>

  <header class="header">
    <div class="header-inner">
      <nav class="header-nav">
        <a href="top" class="header-logo-link">
          <h1 class="header-logo">Circle</h1>
        </a>

        <ul class="header-nav__items">
          <li class="header-nav__item header-nav__name"><%=user.getName() %></li
<%
	if(user.isAdmin()){
%>
          <li class="header-nav__item header-nav__authority">管理画面へ</li>
<%
	}
%>
        </ul>
      </nav>
    </div>
  </header>

	