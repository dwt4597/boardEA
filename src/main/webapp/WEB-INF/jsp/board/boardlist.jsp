<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="../common/common.jsp" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회</th>
		</tr>
		<c:forEach items="${boardlist}" var="map">
			<tr>
				<td>${map.boardid}</td>
				<td><a href="boardView.do?brdid=${map.boardid}">${map.title}</a></td>
				<td>${map.userid}</td>
				<td>${map.writetime}</td>
				<td>${map["SEECOUNT"]}</td>
			</tr>
		</c:forEach>
		
		
	</table>
	
	
	
</body>
</html>