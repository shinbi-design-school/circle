<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.design_shinbi.circle.model.entity.User" %>
<%@ page import="com.design_shinbi.circle.model.Const" %>

<%
	User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
	String iconFileName = user.getIconFileName();
	
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" href="css/mypage.css">
    <link rel="stylesheet" type="text/css" href="css/header.css">
  	<link rel="stylesheet" type="text/css" href="css/transition.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <title>user</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1 class="mypage_title">INFORMATION</h1>
    <div class="mypage_information_container">
    <div class="mypage_icon">
        <form id="new_icon_form" method="post" action="upload" enctype="multipart/form-data">
        <img class="mypage_icon_image" src="icon?id=<%= user.getId() %>">
            <div id="icon_file_name"></div>
            <input id="user_icon_file" type="file" name="icon_file">
            <a href="javascript:removeIcon()">
                <span class="icon_red fas fa-times_circle"></span>
            </a>
            <input id="submit" type="submit" name="submit" value="更新">
    </div>
        <input type="hidden" name="id" vale="<%= user.getId() %>">
        <input type="hidden" name="delete_icon_flag" value="false">
        </form>
        <div class="mypage_information_table">
            <table>
                <tr>
                    <th>NAME：</th>
                    <td>どどど</td>
                </tr>
                <tr>
                    <th>E-MAIL：</th>
                    <td>sss@example.com</td>
                </tr>
                <tr>
                    <th>PASSWORD：</th>
                    <td>・・・・・・・・・</td>
                </tr>
                <tr>
                    <th>
                        <a href="" class="fas fa-pencil-alt"></a>
                    </th>

                    <th>
                        <a href="" class="fa-solid fa-trash-can"></a>
                    </th>
                </tr>
            </table>
        </div>
    </div>
    <hr>
    <div class="mypage_container">
        <div class="mypage_history">
            <h3>プレイ履歴</h3>
            <p>2023/0710</p>
            <p>2023/0709</p>
            <p>2023/0702</p>
            <p>2023/0701</p>
        </div>
        <div class="mypage_ranking">
            <h3>ランキング</h3>
            <p>たかし</p>
            <p>たかこ</p>
            <p>たかだ</p>
        </div>
        <div class="mypage_score">
            <h3>最高スコア</h3>
            <p>100</p>
            <p>100</p>
            <p>100</p>
        </div>
</div>	
</body>
</html>