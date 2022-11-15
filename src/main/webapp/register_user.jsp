<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	ユーザ追加画面
	<form action="Register" method="POST">
		名前: <input type="text" name="r_name"><br>
		メールアドレス: <input type="text" name="r_email_address"><br>
		パスワード: <input type="password" name="r_pass"><br>
		
		<input type="submit" value="登録">
	</form>
</body>
</html>