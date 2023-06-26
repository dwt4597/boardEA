<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세페이지</title>
</head>
<body>

	<div>작 성 자 : ${userid}</div>
	<div>제 목  : ${title}</div>
<div>내 용  :  <textarea readonly>${boardcontents}</textarea></div><br/>
<a href=""><div>목 록 보 기 </div></a>

</body>
</html>