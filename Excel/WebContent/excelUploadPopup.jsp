<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>
<article align="center">
<h1>엑셀파일로 자료 올리기</h1>
<form name="file" action="excelUp.do" method="post" enctype="multipart/form-data">
<fieldset>
<input type="file" name="file" accept=".xlsx">
</fieldset>
<br><br><input type="submit" value="적용하기">
</form>
</article>
</section>
</body>
</html>