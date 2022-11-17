<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>비밀번호 찾기</h4>
<form action="findPW" method="post">
   아이디를 입력하세요 : <input name="userId"><br>
   이메일 주소를 입력하세요 : <input name="emailAdress1">@
  <select name="emailAdress2">
  	<option value="naver.com">naver.com</option>
  	<option value="daum.net">daum.net</option>
  </select>&nbsp;
  <input type="submit" value="비밀번호 찾기">
</form>
</body>
</html>