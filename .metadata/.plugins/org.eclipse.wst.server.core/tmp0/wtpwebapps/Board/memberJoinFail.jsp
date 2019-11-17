<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function goIndex() {
	window.opener.location.href="index.do";
}
</script>
</head>
<body>
<h1>가입 실패</h1>
<section>
<article>
<input type="button" value="확인" onclick="goIndex();window.close();"/>
</article>
</section>
</body>
</html>