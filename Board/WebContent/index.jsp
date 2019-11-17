<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String userId = (String) session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판게시판게시판</title>
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
<%
if (userId == null || userId.equals("")) {
%>
	<form name="memberJoin" action="memberJoin.do" method="post">
	<table align="center">
	<tr>
	<td colspan="2"><h1>회원가입</h1></td>
	</tr>
	<tr>
	<td>아이디</td>
	<td><input type="text" name="userId" required="required"></td>
	</tr>
	<tr>
	<td>비밀번호</td>
	<td><input type="password" name="pwd" required="required"></td>
	</tr>
	<tr>
	<td>이름</td>
	<td><input type="text" name="name" required="required"></td>
	</tr>
	<tr>
	<td colspan="2">
		<br>
		<input type="submit" value="가입">&nbsp;
		<input type="button" name="login" value="로그인" onclick="location.href='loginForm.do'">
	</td>
	</tr>
	</table>
	</form>
<%
} else {
%>
	<div align="center">
	<h1><%= userId %>님 로그인 중</h1>
	<input type="button" value="로그아웃" onclick="location.href='logout.do'">
	<input type="button" value="게시판" onclick="location.href='list.do'">
	</div>
<% 
}
%>
</article>
</section>
</body>
</html>