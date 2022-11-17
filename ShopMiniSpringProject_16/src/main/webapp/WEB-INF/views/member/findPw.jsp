<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty pwInfo}">
	<h4>비밀번호 변경</h4>
	새 비밀번호 : &nbsp;<input name="newPW"><br>
	비밀번호 확인: <input name="checkPW"><br>
	<a href="loginForm">로그인하러 가기</a>
</c:if>
<c:if test="${empty pwInfo}">
	<h4>회원님의 정보가 없습니다</h4>
	<a href="memberForm">회원가입하러 가기</a>
</c:if>

</body>
</html>