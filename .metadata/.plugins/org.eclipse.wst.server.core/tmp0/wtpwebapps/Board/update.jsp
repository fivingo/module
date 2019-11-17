<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function updateResult() {
	var result = "<%= request.getAttribute("result") %>"
	
	if (result > 0) {
		window.open("updateSuccess.do", "result", "width=300, height=200, top=200, left=700");
	} else {
		window.open("updateFail.do", "result", "width=300, height=200, top=200, left=700");
	}
}
</script>
</head>
<body onload="updateResult();">

</body>
</html>