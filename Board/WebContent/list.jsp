<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="list.model.*" %>
<jsp:useBean id="dao" class="list.model.ListDAO" scope="session"/>
<%
// 검색 파라미터값 얻기
String search_s = request.getParameter("search_s");
if (search_s != null) search_s = search_s.equals("null") ? null : search_s;
String search_t = request.getParameter("search_t");
if (search_t == null) search_t = "";

// 1. 총 게시물 수
int totalCount = dao.getTotalCount();
// 검색된 총 게시물 수
if (search_s != null && search_t != null) {
	totalCount = dao.getTotalCount_search(search_s, search_t);
}

// 2,3. 보여줄 리스트 수, 페이지 블록 크기 (임의지정가능)
int listSize = 10;
int pageBlockSize = 3;

// 4. 사용자의 현재 위치 (선택한 페이지)
String cPage_s = request.getParameter("cPage");
if (cPage_s == null || cPage_s.equals("")) {
	cPage_s = "1";
}
int cPage = Integer.parseInt(cPage_s);

// 총 페이지 수
int totalPage = (totalCount / listSize) + 1;
if (totalCount % listSize == 0) {
	totalPage --;
}

// 4-1. 그룹화
int userGroup = cPage / pageBlockSize;
if (cPage % pageBlockSize == 0) {
	userGroup--;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
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
	border: 1px solid gray;
}
</style>
</head>
<body>
<section>
<article>
<!-- 
 <form name="memberJoin" action="memberJoin.do" method="post">
 -->
<table align="center">
<thead>
	<tr>
	<td colspan="5"><h1>게시물 목록</h1></td>
	</tr>
	<tr style="background-color: yellow">
	<td>번호</td>
	<td>제목</td>
	<td>작성자</td>
	<td>작성일자</td>
	<td>조회수</td>
	</tr>
</thead>
<tfoot>
	<tr>
	<td colspan="5">
<%
	// <<
	if (userGroup != 0) {
		if (search_s != null && search_t != null) {
%>
			<a href="list.do?cPage=<%= (userGroup - 1) * pageBlockSize + pageBlockSize %>&search_s=<%= search_s %>&search_t=<%= search_t %>">&lt;&lt;</a>
<%
		} else {
%>
			<a href="list.do?cPage=<%= (userGroup - 1) * pageBlockSize + pageBlockSize %>">&lt;&lt;</a>
<%
		}
	}
%>	
<%
	// 4-2. 그룹화 후 블록 보이기
	for (int i = (userGroup * pageBlockSize + 1); i <= (userGroup * pageBlockSize + pageBlockSize); i++) {
		if (search_s != null && search_t != null) {
%>
			&nbsp;<a href="list.do?cPage=<%= i %>&search_s=<%= search_s %>&search_t=<%= search_t %>"><%= i %></a>&nbsp;
<%
		} else {
%>
			&nbsp;<a href="list.do?cPage=<%= i %>"><%= i %></a>&nbsp;
<%
		}
		if (i == totalPage) break;
	}
%>
<%
	// >>
	if (userGroup != (totalPage / pageBlockSize) - (totalPage % pageBlockSize == 0 ? 1 : 0)) {
		if (search_s != null && search_t != null) {
%>
			<a href="list.do?cPage=<%= (userGroup + 1 ) * pageBlockSize + 1 %>&search_s=<%= search_s %>&search_t=<%= search_t %>">&gt;&gt;</a>
<%
		} else {
%>
			<a href="list.do?cPage=<%= (userGroup + 1 ) * pageBlockSize + 1 %>">&gt;&gt;</a>
<%
		}
	}
%>
	</td>
	</tr>
	<tr>
	<td colspan="5">
	<input type="button" value="새글쓰기" onclick="location.href='writeForm.do'">
	<form name="search" action="list.do" method="post">
	<select name="search_s">
		<option value="title">제목</option>
		<option value="userId">작성자</option>
	</select>
	<input type="text" name="search_t" size="10px">
	<input type="submit" value="검색">
	</form>
	</td>
	</tr>
</tfoot>
<tbody>
	<%
	// 리스트 DB로부터 받아오기
	ArrayList<ListDTO> arr = dao.list(cPage, listSize);
	
	// 검색된 리스트 DB로부터 받아오기
	if (search_s != null && search_t != null) {
		arr = dao.listSearch(cPage, listSize, search_s, search_t);
	}
	
	if (arr == null || arr.size() == 0) {
	%>
		<tr>
		<td colspan="5" align="center">
		등록된 글이 없습니다.</td>
		</tr>
	<%
	} else {
		for (int i = 0; i < arr.size(); i++) {
	%>
			<tr>
			<!-- 페이징 글번호 역순 -->
			<td><%= totalCount - (cPage - 1) * listSize - i %></td>
			<td align="left">
			<%
			// 답변 들여쓰기
			for (int j = 1; j <= arr.get(i).getStep(); j++) {
				out.println("&nbsp;&nbsp;");
			}
			%>
			<a href="content.do?boardId=<%= arr.get(i).getBoardId() %>">
			<%= arr.get(i).getTitle()%></a></td>
			<td><%= arr.get(i).getUserId() %></td>
			<td><%= arr.get(i).getCreateDate() %></td>
			<td><%= arr.get(i).getViewCount() %></td>
			</tr>
	<%
		}
	}
	%>
</tbody>
</table>
<!-- 
</form>
 -->
</article>
</section>
</body>
</html>