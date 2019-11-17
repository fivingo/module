<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function joinResult() {
	var result = "<%= request.getAttribute("msg") %>";
	
	if (result == '가입 완료') {
		window.open("memberJoinSuccess.do", "result", "width=300, height=200, top=200, left=700");
		//location.href="login.do";
	} else {
		window.open("memberJoinFail.do", "result", "width=300, height=200, top=200, left=700");
		//location.href="index.do";
	}
}
</script>
</head>
<body onload="joinResult();">

</body>
</html>