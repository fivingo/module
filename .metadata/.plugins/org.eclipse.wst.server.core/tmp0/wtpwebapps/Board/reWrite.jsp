<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function reWriteResult() {
	/*
	var result = "<%= request.getAttribute("result") %>"
	window.alert(result);
	*/
	
	location.href="list.do";
	
	/*
	if (result > 0) {
		window.open("reWriteSuccess.do", "result", "width=300, height=200, top=200, left=700");
	} else {
		window.open("reWriteFail.do", "result", "width=300, height=200, top=200, left=700");
	}
	*/
}
</script>
</head>
<body onload="reWriteResult();">

</body>
</html>