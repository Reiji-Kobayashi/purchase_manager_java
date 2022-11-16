<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<br>

	<%
		ResultSet rset = (ResultSet) request.getAttribute("user_list");
	%>
	
	<table border="1">
		<tr bgcolor="#cccccc">
			<td><b>ID</b></td>
			<td><b>名前</b></td>
			<td><b>メールアドレス</b></td>
			<td><b>役職</b></td>
		</tr>
		
		<%
			while (rset.next()) {
		%>
		
		<tr>
			<td><%=rset.getString(1)%></td>
			<td><%=rset.getString(2)%></td>
			<td><%=rset.getString(3)%></td>
			<td><%=rset.getString(4)%></td>
		</tr>
		
		<%
			}
		%>
	</table>

	<br>
</body>
</html>