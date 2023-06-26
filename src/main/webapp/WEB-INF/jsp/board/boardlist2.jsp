<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--jstl 태그라이브러리 추가.-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table {
		  border-collapse: collapse;
		margin:100px auto;
		width: 800px;
		height: auto;
	}
	
	table, th, td {
	  border: 1px solid black;
	}
	
	tr
	{
	height: 34px;
	}
	
	.col1
	{
	width:65px;
	}
	.col2
	{
	width:385px;
	}
	.col3
	{
	width:85px;
	}
	
	a
	{
	color:black;
	text-decoration:none;
	}

</style>
</head>
<body>
	<table>
		<tr>
			<th class="col1">번호</th>
			<th class="col2">제목</th>
			<th class="col3">글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach items="${boardlist}" var="map">
		<tr>
			<td>${map.boardid}</td>
			<td><a href="boardView.do?brdid=${map.boardid}">${map.title}</a></td>
			<td>${map.userid}</td>
			<td>${map.writetime}</td>
			<td>${map["seecount"]}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
