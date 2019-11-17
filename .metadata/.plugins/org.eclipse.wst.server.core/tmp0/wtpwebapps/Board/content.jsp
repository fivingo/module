<%@page import="file.model.FileDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="list.model.ListDTO" %>
<%@ page import="comment.model.CommentDTO" %>
<jsp:useBean id="listDao" class="list.model.ListDAO" scope="session"/>
<jsp:useBean id="commentDao" class="comment.model.CommentDAO" scope="session"/>
<jsp:useBean id="fileDao" class="file.model.FileDAO" scope="session"/>

<%
//request.setCharacterEncoding("UTF-8");

String boardId_s = request.getParameter("boardId");

if (boardId_s == null || boardId_s.equals("")) {
	boardId_s = "0";
}
int boardId = Integer.parseInt(boardId_s);
ListDTO listDto = listDao.content(boardId);

if (listDto == null) {
%>
	<script>
	window.alert('삭제된 게시글이거나 잘못된 접근입니다.');
	location.href='list.do';
	</script>
<%
	return;
}
// 쿠키 가져오기
Cookie[] cookies = request.getCookies();
String cValue = null;
if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		Cookie c = cookies[i];
		cValue = c.getValue();
	}
}

// 조회수 증가 및 쿠키 생성
if (!cValue.equals(boardId_s)) {
	listDao.viewCount(boardId, listDto.getViewCount());
	Cookie cookie = new Cookie("viewCookie", boardId_s);
	cookie.setMaxAge(60 * 60 * 24);
	response.addCookie(cookie);
}

// 로그인중인 세션 가져오기
String userId = (String) session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function update() {
	location.href='updateForm.do?boardId=<%= listDto.getBoardId() %>&title=<%= listDto.getTitle() %>&userId=<%= listDto.getUserId() %>&viewCount=<%= listDto.getViewCount() %>&content=<%= listDto.getContent() %>';
}
function delcontent() {
	location.href='delete.do?boardId=<%= listDto.getBoardId() %>';
}
function commentReply(index){
	window.alert('왜 안돼 또');
	
	var con = document.getElementById('commentReply' + index);
	
	if (con.style.display == 'none') {
		con.style.display = '';
	} 
}
function creationPopup() {
	var boardId = <%= listDto.getBoardId() %>;
	var userId = '<%= listDto.getUserId() %>';
	
	window.open("creationPopup.do?url=" + location.href + "&boardId=" + boardId + "&userId=" + userId, "creation", "width=900, height=400, top=200, left=300");
}
</script>
<style type="text/css">
table {
	border: 2px;
	magin: auto;
	text-align: center;
	width: 600px;
	border-spacing: 0px;
}
td {
	padding: 10px;
	/*border: 1px solid gray;*/
}
</style>
</head>
<body>
<section>
<article>
<form name="write" action="write.do" method="post">
<table align="center">
<tr>
<td colspan="4"><h1>게시물 상세</h1></td>
</tr>
<tr>
<td>제목</td>
<td colspan="2">
<input type="text" name="title" value="<%= listDto.getTitle() %>" style="width:370px" readonly="readonly">
</td>
<%-- 특정 아이디 버튼 보이기 --%>
<% 
if (userId.equals("test")) {
%>
<td>
<input type="button" value="QR코드/단축URL생성" onclick="creationPopup()">
</td>
<%
}
%>
</tr>
<tr>
<td>작성자</td>
<td>
<input type="text" name="userId" value="<%= listDto.getUserId() %>" style="width:300px" readonly="readonly">
</td>
<td>조회수</td>
<td>
<input type="text" name="viewCount" value="<%= listDto.getViewCount() %>" style="width:300px" readonly="readonly">
</td>
</tr>
<tr>
<td>내용</td>
<td colspan="3"><textarea name="content" rows="20" cols="95" readonly="readonly">
<%= listDto.getContent() %></textarea></td>
</tr>
</table>
</form>
<!-- 파일리스트 -->
<table align="center">
<tr>
<td width=50>파일</td>
<td width=850>
<%
ArrayList<FileDTO> fileArr = fileDao.fileList(listDto.getBoardId());
//String savePath = "/BoardStudy/WebContent/FileUpload";

for (int i = 0; i < fileArr.size(); i++) {
%>
	<a href="fileDownload.do?maskFile=<%= fileArr.get(i).getMaskName() %>&oriFile=<%= fileArr.get(i).getOriginalName() %>&boardId=<%= listDto.getBoardId()%>">
	<%= fileArr.get(i).getOriginalName() %></a><br>
<%
}
%>
</td>
</tr>
</table>
<!-- button -->
<table align="center">
<tr>
<td colspan="4">
	<%
	if (userId.equals(listDto.getUserId())) {
	%>
		<input type="button" value="수정" onclick="update();">&nbsp;
		<%
		if (listDao.checkReply(listDto) == 0) {	// 답글 있으면 삭제 불가
			%>
			<input type="button" value="삭제" onclick="delcontent();">&nbsp;
			<%
		}
		%>		
	<%
	}
	%>
	<input type="button" value="답변" 
	onclick="location.href='reWriteForm.do?title=<%= listDto.getTitle() %>&ref=<%= listDto.getRef() %>&step=<%= listDto.getStep() %>&position=<%= listDto.getPosition() %>'">
	<input type="button" value="목록보기" onclick="location.href='list.do'">
</td>
</tr>
</table>
<!-- 댓글 -->
<form name="comment" action="comment.do" method="post">
<table align="center">
<tr>
<td colspan="3">
	<br>
	<input type="text" name="content_c" style="width:500px">
	<input type="hidden" name="boardId_c" value="<%= listDto.getBoardId() %>">
	<input type="hidden" name="userId_c" value="<%= session.getAttribute("userId") %>">
</td>
<td>
	<input type="submit" value="댓글등록">
</td>
</tr>
</table>
</form>
<%
// 댓글 리스트 DB로부터 받아오기
ArrayList<CommentDTO> comArr = commentDao.commentList(listDto.getBoardId());

for (int i = 0; i < comArr.size(); i++) {
%>
	<form name="commentReply" action="commentReply.do" method="post">
	<table align="center">
	<tr>
	<td width=80><%= comArr.get(i).getUserId() %></td>
	<td colspan="2"  width=300 style="text-align:left">
	<%
	// 답변 들여쓰기
	for (int j = 1; j <= comArr.get(i).getStep(); j++) {
		out.println("&nbsp;&nbsp;");
	}
	%>
	<%= comArr.get(i).getContent() %></td>
	<td><a href="javascript:commentReply(<%= i %>)">답글쓰기</a>
	<!-- <input type="button" value="답글쓰기" onclick="commentReply()"/> -->
	</td>
	</tr>
	<tr id="commentReply<%= i %>" style="display:none">
	<td colspan="3">
	<input type="text" name="content_re" placeholder="=>">
	<input type="hidden" name="boardId_re" value="<%= listDto.getBoardId() %>">
	<input type="hidden" name="userId_re" value="<%= session.getAttribute("userId") %>">
	<input type="hidden" name="ref_re" value="<%= comArr.get(i).getRef() %>">
	<input type="hidden" name="step_re" value="<%= comArr.get(i).getStep() %>">
	<input type="hidden" name="position_re" value="<%= comArr.get(i).getPosition() %>">
	</td>
 	<td><input type="submit" value="답변등록"></td>
	</tr>
	</table>
	</form>
<%
}
%>
</article>
</section>
</body>
</html>