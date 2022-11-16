<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	ユーザ検索 <br><br>
	
	<form action="SearchUser" method="POST">
		ID: <input type="text" name="s_id"> <br>
		名前: <input type="text" name="s_name"> <br>
		メールアドレス: <input type="text" name="s_email_address"> <br>
		
		役職:<SELECT NAME="s_role">
			<OPTION VALUE="" selected></OPTION>
			<OPTION VALUE="admin">admin</OPTION>
			<OPTION VALUE="member">member</OPTION>
			<OPTION VALUE="guest">guest</OPTION>	
		</SELECT> <br><br>
		<input type="submit" value="検索">
	</form>
</body>
</html>