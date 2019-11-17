<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String userId = (String) session.getAttribute("userId"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<script>
tinymce.init({selector:'textarea'});
</script>
 --%>
<script>
var count = 1;
var addCount;

function addFileUpload() {
	for (var i = 1; i <= count; i++) {
		if (!document.getElementsByName("file" + i)[0]) {
			addCount = i;
			break;
		} else {
			addCount = count;
		}
	} 
	var addStr = "<tr><td><input type=file name=file"+addCount+"></td></tr>";
	var table = document.getElementById("filesTable");
	var newRow = table.insertRow();
	var newCell = newRow.insertCell();
	newCell.innerHTML = addStr;
	count++;
	
	var fileCount = document.getElementById("fileCount");
	fileCount.value = addCount;
}
</script>
<style type="text/css">
table {
	border: 2px;
	magin: auto;
	text-align: center;
}
</style>
</head>
<body><section>
<article>
<form name="write" action="write.do" method="post" enctype="multipart/form-data">
<table align="center">
<tr>
<td colspan="4"><h1>게시물 등록</h1></td>
</tr>
<tr>
<td>제목</td>
<td colspan="3"><input type="text" name="title" style="width:700px" required="required"></td>
</tr>
<tr>
<td>작성자</td>
<td>
<input type="text" name="userId" style="width:300px" value="<%= userId %>" readonly="readonly">
</td>
<td>조회수</td>
<td><input type="text" name="viewCount" style="width:300px" readonly="readonly"></td>
</tr>
<tr>
<td>내용</td>
<td colspan="3"><textarea name="content" rows="20" cols="100" required="required"></textarea></td>
</tr>
<tr>
<td>파일</td>
<td>
<input type="hidden" name="fileCount" id="fileCount" value="">
<table id="filesTable">
</table>
</td>
</tr>
<tr>
<td colspan="4">
	<br>
	<input type="reset" value="취소">&nbsp;
	<input type="submit" value="저장">&nbsp;
	<input type="button" value="파일추가" onclick="addFileUpload();">
	<input type="button" value="목록보기" onclick="location.href='list.do'">
</td>
</tr>
</table>
</form>
</article>
</section>
</body>
</html>