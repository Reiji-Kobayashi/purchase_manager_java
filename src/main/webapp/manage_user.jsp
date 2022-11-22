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
		<tr bgcolor="#cccc">
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
			<td><a href="ManageUser?&mode=delete&id=<%=rset.getString(1)%>">削除</a></td>
			<td>
				<form action="ManageUser" method="POST">
					<input type="hidden" name="mode" value="change"> 
					<input type="hidden" name="id" value="<%=rset.getString(1)%>">
					<input type="hidden" name="name" value="<%=rset.getString(2)%>">
					<input type="hidden" name="email_address" value="<%=rset.getString(3)%>">
					<input type="hidden" name="role" value="<%=rset.getString(4)%>">
					<input type="submit" value="変更">
				</form>
			</td>
		</tr>
		
		<%
			}
		%>
	</table>

	<br>
</body>
</html>