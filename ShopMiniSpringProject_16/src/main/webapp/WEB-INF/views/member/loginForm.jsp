<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${!empty mesg}">
  <script>
	alert('${mesg}');
  </script>
</c:if>

<% if(session.getAttribute("mesg")!=null) {
	session.removeAttribute("mesg");
} %>
	
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("form").on("submit", function(event) {       //event는 왜 쓰는건지?
			var userid =$("#userid").val();
			var passwd =$("#passwd").val();
			if (userid.length==0) {
				alert("userid 필수. 아이디를 입력해주세요");
				$("#userid").focus();
				event.preventDefault();
			}else if (passwd.length==0) {
				alert("passwd 필수. 비밀번호를 입력해주세요");
				$("#passwd").focus();
				event.preventDefault();
			}
		});
	});
</script>

<form action="login" method="post">
아이디:&nbsp;<input type="text" name="userid"><br>
비밀번호:&nbsp;<input type="text" name="passwd"><br>
<input type="submit" value="로그인">
<input type="reset" value="취소">
</form>
<a href="idForm">아이디 찾기</a>&nbsp;
<a href="pwForm">비밀번호 찾기</a>
