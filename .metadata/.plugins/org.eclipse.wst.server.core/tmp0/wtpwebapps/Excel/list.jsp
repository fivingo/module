<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="excel.model.*" %>
<jsp:useBean id="dao" class="excel.model.ExcelDAO" scope="session"/>
<%
// 총 게시물 수
int totalCount = dao.getTotalCount();

// 보여줄 리스트 수, 페이지 블록 크기
int listSize = 10;
int pageBlockSize = 4;

// 사용자의 현재 페이지 (선택 페이지)
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

// 그룹화
int userGroup = cPage / pageBlockSize;
if (cPage % pageBlockSize == 0) {
	userGroup--;
}
%>
<%----------------------------------------------------------------------------------%>
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
<table align="center">
<thead>
	<tr>
	<td>학번</td>
	<td>이름</td>
	<td>학과</td>
	<td>지도교수</td>
	<td>입학일</td>
	<td>주소</td>
	</tr>
</thead>
<%----------------------------------------------------------------------------------%>
<tfoot>
<tr>
<td colspan="6">
<%
// 리스트 DB로부터 받아오기
ArrayList<ExcelDTO> arr = dao.list(cPage, listSize);

if (arr == null || arr.size() == 0) {
%>
	<tr>
	<td colspan="6">데이터가 없습니다.</td>
	</tr>
	<tr>
	<td>
<%
} else {
	// pre
	if (userGroup != 0) {
%>
		<a href="javascript:ajaxListShow(<%= (userGroup - 1) * pageBlockSize + pageBlockSize %>)">pre</a>
<%
	}
%>

<%
	// 그룹화 후 블록 보이기
	for (int i = (userGroup * pageBlockSize + 1); i <= (userGroup * pageBlockSize + pageBlockSize); i++) {
%>
		&nbsp;<a href="javascript:ajaxListShow(<%= i %>)"><%= i %></a>&nbsp;
<%
		if (i == totalPage) break;
	}
%>

<%
	// next
	if (userGroup != (totalPage / pageBlockSize) - (totalPage % pageBlockSize == 0 ? 1 : 0)) {
%>
		<a href="javascript:ajaxListShow(<%= (userGroup + 1 ) * pageBlockSize + 1 %>)">next</a>
<%
	}
%>
	</td>
	</tr>
	<tr>
	<td colspan="4">
	<td><input type="button" value="엑셀다운로드" onclick="ajaxExcelDown(<%= cPage %>)"></td>
	<td><input type="button" value="엑셀다운로드(학사경고)" onclick="ajaxExcelDownPro(<%= cPage %>)"></td>
	</tr>
	</tfoot>
<%----------------------------------------------------------------------------------%>
	<tbody>
<%

	for (int i = 0; i < arr.size(); i++) {
%>
		<tr>
		<td><%= arr.get(i).getStudentNum() %></td>
		<td><%= arr.get(i).getName() %></td>
		<td><%= dao.getMajor(arr.get(i).getMajorCode()) %></td>
		<td><%= dao.getProfessor(arr.get(i).getProCode()) %></td>
		<td><%= arr.get(i).getAdmission().substring(0, 10) %></td>
		<td><%= arr.get(i).getAddr() %></td>
		</tr>
<%
	}
%>
	</tbody>
<%
}
%>
</table>