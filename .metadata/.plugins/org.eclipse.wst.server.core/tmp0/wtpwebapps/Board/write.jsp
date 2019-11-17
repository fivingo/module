<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function writeResult() {
	var result = "<%= request.getAttribute("result") %>"
	
	if (result > 0) {
		window.open("writeSuccess.do", "result", "width=300, height=200, top=200, left=700");
	} else {
		window.open("writeFail.do", "result", "width=300, height=200, top=200, left=700");
	}
}
</script>
</head>
<body onload="writeResult();">

</body>
</html>