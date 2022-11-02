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
		String message = (String)request.getAttribute( "login" );
		String name = (String)request.getAttribute( "name" );
		if(name == null){message = "ログイン情報がありません";}
	%>

	<br><br>
 	<%= message %> <br><br>
 
	こんにちは <%= name %> さん<br><br>
</body>
</html>