<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>아이디 찾기</h4>
<form action="findID" method="post">
   이름을 입력하세요 : <input name="userName"><br>
   이메일 주소를 입력하세요 : <input name="emailAdress1">@
  <select name="emailAdress2">
  	<option value="naver.com">naver.com</option>
  	<option value="daum.net">daum.net</option>
  </select>&nbsp;
  <input type="submit" value="아이디 찾기">
</form>

</body>
</html>