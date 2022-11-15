<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<br>
	<%
		String message = (String)session.getAttribute( "login" );
		String name = (String)session.getAttribute( "name" );
		String role = (String)session.getAttribute( "role" );
		if(role == null){message = "ログイン情報がありません";}
	%>

	<br><br>
 	<%= message %> <br><br>
 	
	<% if(role != null){ %>
		こんにちは <%= name %> さん<br><br>
	
		アカウント管理<br>
		物品管理<br>
	
		<% if(role.equals("member") || role.equals("admin")){ %>
		物品登録<br>
		<% } %>
		<% if(role.equals("admin")){ %>
		<a href="./admin.jsp">ユーザ管理</a><br>
		<% } %>
	<% } %>
</body>
</html>