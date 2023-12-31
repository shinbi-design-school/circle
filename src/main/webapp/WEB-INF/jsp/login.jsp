<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/circle.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
    <jsp:include page="head.jsp" />
    <title>ログイン</title>
</head>
<body class="login-page">
	<main>
        <div class="login-page-container">
            <h1>Circle</h1>
		    <form action="login" method="POST">
                <table id="login">
                    <tr>
                        <th>メールアドレス</th>
                        <td><input type="email" name="email"></td>
                    </tr>
                    <tr>
                        <th>パスワード</th>
                        <td><input type="password" name="password"></td>
                    </tr>
                </table>
                <input type="submit" value="ログイン" id="submit">
		    </form>
<%
	if(error != null){
%>
		<div id="error"><%=error %></div>
<%
	}
%>
        </div>
        <div class="join-link">
	        <a href="./join">新規登録</a>
        </div>
        <div class="guest-link">
	        <a href="./top">ゲストモード</a>
        </div>
    </main>
</body>
</html>

	</main>
</body>
</html>