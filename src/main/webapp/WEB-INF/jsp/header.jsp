<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.design_shinbi.circle.model.entity.User" %>
<%@ page import="com.design_shinbi.circle.model.Const" %>

<%
	User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
	String userName = null;
	boolean authority = false;
	
	if (user != null){
		userName = user.getName();
		authority = user.isAdmin();		
	} else {
		userName = "Guest";
	}
%>

  <header class="header">
    <div class="header-inner">
      <nav class="header-nav">
        <a href="top" class="header-logo-link">
          <h1 class="header-logo">Circle</h1>
        </a>

        <ul class="header-nav__items">
          <li class="header-nav__item header-nav__name">
			<a href="./user"><%=userName %></a>
          </li>
<%
	if (user != null){
%>
          <li class="header-nav__item header-nav__name">
          	<a href="./logout">ログアウト</a>
          </li>
<%
	} else {
%>
          <li class="header-nav__item header-nav__name">
          	<a href="./join">新規登録</a>
          </li>
<%
	}
%>


<%
	if(authority){
%>
          <li class="header-nav__item header-nav__authority">管理画面へ</li>
<%
	}
%>
        </ul>
      </nav>
    </div>
  </header>

	