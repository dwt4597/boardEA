<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- get: 주소창 뒤에 파라미터 보임 / post : 안보임 -->
	<form action="main3.do" method="post">
		<input type="hidden" name="userNo" value="10">
		<input type="text" name="id" >
		<input type="password" name="pw" >
		<input type="submit" value="제출">
	</form>
</body>
</html>