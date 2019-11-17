<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인로그인</title>
<style type="text/css">
table {
	border: 2px;
	magin: auto;
	text-align: center;
}
</style>
</head>
<body>
<section>
<article>
<form name="login" action="login.do" method="post">
<table align="center">
<tr>
<td colspan="3"><h1>로그인</h1></td>
</tr>
<tr>
<td>아이디</td>
<td><input type="text" name="userId" required="required"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="pwd" required="required"></td>
<td><input type="submit" value="로그인"></td>
</tr>
</table>
</form>
</article>
</section>
</body>
</html>