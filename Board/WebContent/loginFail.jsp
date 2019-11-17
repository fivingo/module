<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function goLogin() {
	window.opener.location.href="loginForm.do";
}
</script>
</head>
<body>
<h1>로그인 실패</h1>
<section>
<article>
<input type="button" value="확인" onclick="goLogin();window.close();"/>
</article>
</section>
</body>
</html>