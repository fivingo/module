<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="list.model.ListDTO"%>
<%@ page import="file.model.FileDTO"%>
<jsp:useBean id="listDao" class="list.model.ListDAO" scope="session"/>
<jsp:useBean id="fileDao" class="file.model.FileDAO" scope="session"/>
<%
// 파라미터 받아올때 인코딩 문제
String boardId_s = new String(request.getParameter("boardId").getBytes("8859_1"), "UTF-8");
int boardId = Integer.parseInt(boardId_s);
String title = new String(request.getParameter("title").getBytes("8859_1"), "UTF-8");
String userId = new String(request.getParameter("userId").getBytes("8859_1"), "UTF-8");
String viewCount = new String(request.getParameter("viewCount").getBytes("8859_1"), "UTF-8");
String content = new String(request.getParameter("content").getBytes("8859_1"), "UTF-8");

if (boardId_s == null || boardId_s.equals("")) {
	boardId_s = "0";
}
ListDTO listDto = listDao.content(boardId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
td {
	padding: 10px;
	/* border: 1px solid gray; */
}
</style>
</head>
<body>
<section>
<article>
<form name="update" action="update.do" method="post" enctype="multipart/form-data">
<table align="center">
<tr>
<td colspan="4"><h1>[게시물 수정]</h1>
<input type="hidden" name="boardId" value="<%= boardId %>"></td>
</tr>
<tr>
<td>제목</td>
<td colspan="3">
<input type="text" name="title" value="<%= title %>" style="width:700px" required="required">
</td>
</tr>
<tr>
<td>작성자</td>
<td>
<input type="text" name="userId" value="<%= userId %>" style="width:315px" readonly="readonly">
</td>
<td>조회수</td>
<td>
<input type="text" name="viewCount" value="<%= viewCount %>" style="width:315px" readonly="readonly">
</td>
</tr>
<tr>
<td>내용</td>
<td colspan="3"><textarea name="content"  rows="20" cols="95" required="required"><%= content %></textarea></td>
</tr>
<tr>
<td>파일</td>
<td colspan="3">
<%
ArrayList<FileDTO> fileArr = fileDao.fileList(listDto.getBoardId());

for (int i = 0; i < fileArr.size(); i++) {
%>
	<input type="hidden" name="savedFile" value="<%= fileArr.size() %>">
	<input type="hidden" name="maskFile" value="<%= fileArr.get(i).getMaskName() %>">
	<input type="checkbox" name="checkDel<%= i + 1 %>" value="<%= fileArr.get(i).getFileId() %>">
	<a href="fileDownload.do?maskFile=<%= fileArr.get(i).getMaskName() %>&boardId=<%= listDto.getBoardId()%>">
	<%= fileArr.get(i).getOriginalName() %></a><br>
<%
}
%>
</td>
</tr>
<tr>
<td colspan="4" align="center">
<input type="hidden" name="fileCount" id="fileCount" value="">
<table id="filesTable">
</table>
</td>
</tr>
<tr>
<td colspan="4">
	<br>
	<input type="submit" value="수정">&nbsp;
	<input type="button" value="취소" onclick="window.location.reload();">&nbsp;
	<input type="button" value="파일추가" onclick="addFileUpload();">
</td>
</tr>
</table>
</form>
</article>
</section>
</body>
</html>