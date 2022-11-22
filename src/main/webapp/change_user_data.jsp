<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="reijikobayashi.purchase_manager.UserBeans"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%
		UserBeans user = (UserBeans) request.getAttribute("user");
	%>

	<br>
	以下から変更できます <br><br>
	<form action="ManageUser" method="POST">
		ID: <%=user.getId()%>(変更不可) <input type="hidden" name="id" value="<%=user.getId()%>"> 
		<input type="hidden" name="name" value="<%=user.getName()%>">
		<input type="hidden" name="email_address" value="<%=user.getEmailAddress()%>">
		役職:<SELECT NAME="role">
			<OPTION VALUE="<%=user.getRole()%>" selected><%=user.getRole()%></OPTION>
			<OPTION VALUE="admin">admin</OPTION>
			<OPTION VALUE="member">member</OPTION>
			<OPTION VALUE="user">user</OPTION>
		</SELECT> 
		<input type="hidden" name="mode" value="changed">
		<input type="submit" value="変更確定">
	</form>
	
	<br>
</body>

</html>