<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String url = request.getParameter("url").replace("localhost", "192.168.0.217");
String boardId = request.getParameter("boardId");
String userId = (String) session.getAttribute("userId");
String QRcode = (String) request.getAttribute("QRcode");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QR 코드 / 단축 URL 생성</title>
<style type="text/css">
table {
	border: 2px;
	magin: auto;
	width: 880px;
	height: 350px;
	border-spacing: 0px;
}
td {
	border: 1px solid gray;
	width: 440px;
}
</style>
</head>
<script>
function copyURL() {
	var url = document.getElementById("shortUrl");
	url.select();
	document.execCommand("Copy");
}
</script>
<body>
<section>
<article>
<div>추출 URL: <%= url %></div><br>
<table>
<tr>
<td style="text-align: center">
<%
if (QRcode == null || QRcode.equals("")) {
%>
	<br><br><br><br><br><br><br><br><br><br>
<%
} else {
%>	
	<img src="QRcode/QRcode_<%= boardId %>.png" alt="QR코오드" width="200" height="200"><br>
<%
}
%>
<input type="button" value="QR 코드 생성" onclick="location.href='createQR.do?url=<%= url %>&boardId=<%= boardId %>&userId=<%= userId %>'">
<%
if (QRcode == null || QRcode.equals("")) {
%>
	<a href="#"><input type="button" value="다운로드"></a>
<%
} else {
%>	
	<a href="/QRcode/QRcode_<%= boardId %>.png"><input type="button" value="다운로드"></a>
<%
}
%>
</td>
<td style="padding-left:20px">
<input type="button" value="단축 URL 생성" onclick="location.href='createURL.do?boardId=<%= boardId %>&url=<%= url %>&userId=<%= userId %>'"><br><br>
<%
String shortUrl = (String) request.getAttribute("shortUrl");
if (shortUrl == null || shortUrl.equals("")) {
%>
	<input type="text" style="width:300px;">
<%
} else {
%>
	<input id="shortUrl" type="text" value="<%= shortUrl %>" style="width:300px;">
<%
}
%>
<input type="button" value="복사" onclick="copyURL()">
</td>
</tr>
</table>
</article>
</section>
</body>
</html>