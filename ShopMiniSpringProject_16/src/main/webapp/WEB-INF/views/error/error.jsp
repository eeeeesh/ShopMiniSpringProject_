<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Error 페이지</h1>
<%
	out.print(exception);
%>
<a href="http://localhost:8084/shopMiniMallProject/">메인화면으로</a> <!-- 절대주소 -->
</body>
</html>