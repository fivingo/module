<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function upComplete() {
	var result = "<%= request.getAttribute("result") %>"
	
	if (result == -1) {
		window.alert('알수없는 오류발생');
		window.location.href = 'excelUploadPopup.do';
	} else if (result == -2) {
		window.alert('이미 업로드된 엑셀파일입니다.');
		window.location.href = 'excelUploadPopup.do';
	} else if (result == -3) {
		window.alert('형식이 다른 엑셀파일입니다.');
		window.location.href = 'excelUploadPopup.do';
	} else {
		window.alert('업로드 완료');
		window.close();
		opener.location.reload();
	}
}
</script>
</head>
<body onload="upComplete()">

</body>
</html>