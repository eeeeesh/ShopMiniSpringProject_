<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="css/top.css">
<c:if test="${empty login}"><!-- 로그인 안된 경우 -->
  <div class="top" id="login">
	<a href="loginForm">로그인</a>&nbsp;<!-- xml에 주소설정 -->
	<a href="loginCheck/cartList">장바구니</a>&nbsp;
	<a href="memberForm">회원가입</a>&nbsp;<!-- xml에 주소설정 -->
  </div>
</c:if>
<c:if test="${!empty login}"><!-- 로그인 된 경우 -->
  <div class="top" id="notlogin">
	안녕하세요? ${login.username}님 !<br>
	<a href="loginCheck/logout">로그아웃</a>&nbsp;
	<a href="loginCheck/myPage">mypage</a>&nbsp;
	<a href="loginCheck/cartList">장바구니</a>&nbsp;
  </div>
</c:if>

