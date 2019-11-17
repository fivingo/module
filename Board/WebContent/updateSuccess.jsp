<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function goList() {
	window.opener.location.href="list.do";
}
</script>
</head>
<body>
<h1>수정 성공</h1>
<section>
<article>
<input type="button" value="목록보기" onclick="goList();window.close();"/>
</article>
</section>
</body>
</html>