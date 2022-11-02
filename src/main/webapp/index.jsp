<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%
		String mongon = "IDとパスワードを入力してください";
	%>
	
	<%=mongon%><br>
	<br>
	
	<form action="Login" method="POST">
		EmailAddress:<input type="text" name="email_address">
		Pass:<input type="text" name="pass"> <br>
		<br>
		
		<input type="submit" value="submit">
	</form>
</body>

</html>