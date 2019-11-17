<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>엑셀엑셀</title>
<script src="js/httpRequest.js"></script>
<script>
function upload() {
	window.open("excelUploadPopup.do", "excel", "width=600, height=400, top=200, left=500");
}
function ajaxListShow(cPage) {
	if (cPage != undefined) {
		var param = 'cPage=' + cPage;
		sendRequest('list.do', param, 'POST', ajaxListShowResult);
	} else {
		sendRequest('list.do', null, 'POST', ajaxListShowResult);
	}
}
function ajaxListShowResult() {
	if (XHR.readyState == 4 && XHR.status == 200) {
		var data = XHR.responseText;
		var divTag = document.getElementById('ajaxListShow');
		
		divTag.innerHTML = data;
	}
}
function ajaxExcelDown(cPage) {
	if (cPage != undefined) {
		var param = 'cPage=' + cPage;
		sendRequest('excelDown.do', param, 'POST', ajaxListShowResult);
	} else {
		sendRequest('excelDown.do', null, 'POST', ajaxListShowResult);
	}
}
function ajaxExcelDownPro(cPage) {
	if (cPage != undefined) {
		var param = 'cPage=' + cPage;
		sendRequest('excelDown_pro.do', param, 'POST', ajaxListShowResult);
	} else {
		sendRequest('excelDown_pro.do', null, 'POST', ajaxListShowResult);
	}
}
</script>
</head>
<body>
<section>
<article align="center">
<a href="javascript:upload();">엑셀파일로 자료 올리기</a>
<br><br>
<a href="javascript:ajaxListShow();">리스트로 자료 보기</a>
<br><br>
<div id="ajaxListShow">
<%-- ajax 삽입 위치 --%>
</div>
</article>
</section>
</body>
</html>