<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function totalXXX() {
	var totalSum=0;
	$(".sum").each(function(idx, data) {
		totalSum += Number.parseInt($(data).text());
	});
	$("#totalSum").text(totalSum);
	
}
	$(document).ready(function() {
		totalXXX(); //총합구하는 함수 호출
		
		/* $(".updateBtn").on("click", function() {
			var num = $(this).attr("data-num");
			var price = $(this).attr("data-price");
			var amount = $("#cartAmount"+num).val();
			var sum = $("#sum"+num).text();
			console.log("num:"+num);
			console.log("price:"+price);
			console.log("amount:"+amount);
			console.log("sum:"+sum);
		}) */
		
		$(".updateBtn").on("click", function() {
			var num = $(this).attr("data-num");
			var gAmount = $("#cartAmount"+num).val(); //카트번호이용 class선택하여 수량을 가져옴
			var gPrice = $(this).attr("data-price");
			var sum = $("#sum"+num).text();
			console.log(num, gPrice);
			
			$.ajax({
				url: "loginCheck/cartUpdate",
				type: "get",
				dataType: "text",
				data: {
					num: num,
					amount: gAmount
				},
				success: function(data, status, xhr) {
					var total= parseInt(gAmount)*parseInt(gPrice); //성공시 받은 데이터는 없이 토탈 가격만 변동시킴
					$("#sum"+num).text(total);
					totalXXX();
				},
				error: function(xhr, status, error) {
					console.log(error);
				}
			}); //end ajax
		})
		
		$(".deleteBtn").on("click", function() {
			var num= $(this).attr("data-num");
			var xxx= $(this); //클릭된 삭제버튼 자체
			
			$.ajax({
				url: "loginCheck/cartDelete",
				type: "get",
				dataType: "text",
				data: {
					num: num
				},
				success: function(data, status, xhr) {
					console.log("success");
					//dom 삭제
					xxx.parents().filter("tr").remove();	//tr태그 삭제
					totalXXX();
				},
				error: function(xhr, status, error) {
					console.log(error);
				}
			}); //end ajax
		})
		//전체선택
		$("#allCheck").on("click", function() {
			var result= this.checked;
			$(".check").each(function(idx, data) {
				data.checked= result;
			});
		});
		//전체삭제
		$("#delAllCart").on("click", function() {
			$("form").attr("action","loginCheck/delAllCart");
			$("form").submit(); //trigger
		})
		//주문하기
		$(".orderBtn").on("click", function() {
			var num =$(this).attr("data-num");
			location.href="loginCheck/orderConfirm?num="+num;
		});
		
		
	})
</script>

<table width="90%" cellspacing="0" cellpadding="0" border="0">

	<tr>
		<td height="30">
	</tr>

	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>

	<tr>
		<td height="7">
	</tr>

	<tr>
		<td class="td_default" align="center">
		<input type="checkbox"
		name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	
	
	
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>



	<form name="myForm">
	    
	    
<!-- 반복시작 -->
<c:forEach var="x" items="${cartList}">

		<!-- 	<input type="text" name="num81" value="81" id="num81">
			<input type="text" name="gImage81" value="bottom1" id="gImage81">
		 <input type="text" name="gName81" value="제나 레이스 스커트" id="gName81">
		  <input type="text" name="gSize81" value="L" id="gSize81">
		   <input type="text" name="gColor81" value="navy" id="gColor81"> 
		   <input type="text" name="gPrice81" value="9800" id="gPrice81"> -->

		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 
			따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox"
				name="check" id="check81" class="check" 
				value="${x.num}"></td>
			<td class="td_default" width="80">${x.num}</td>
			<td class="td_default" width="80"><img
				src="images/items/${x.gImage}.gif" border="0" align="center"
				width="80" /></td>
			<td class="td_default" width="300" style='padding-left: 30px'>
				${x.gName}
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(${x.gSize})
					, 색상${x.gColor}()]
			</font></td>
			<td class="td_default" align="center" width="110">
			<span id="ggPrice${x.num}">${x.gPrice}</span>
			</td>
			<td class="td_default" align="center" width="90"><input
				class="input_default" type="text" name="cartAmount"
				id="cartAmount${x.num}" style="text-align: right" maxlength="3"
				size="2" value="${x.gAmount}"></input></td>
			<td><input type="button" value="수정"
				class="updateBtn" 
				data-num="${x.num}"
				data-price="${x.gPrice}" /></td>
			<td class="td_default" align="center" width="80"
				style='padding-left: 5px'><span id="sum${x.num}" class="sum">
				${x.gPrice * x.gAmount}
				</span></td>
			<td><input type="button" value="주문" class="orderBtn"
				data-num="${x.num}"></td>
			<td class="td_default" align="center" width="30"
				style="padding-left: 10px">
				<input type="button" value="삭제" class="deleteBtn"
				data-num="${x.num}"></td>
			<td height="10"></td>
		</tr>

 <!-- 반복끝 --> 
</c:forEach>
	</form>
	<tr>
		<td colspan="10">
		총합 : <span id="totalSum"></span>	
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td align="center" colspan="5"><a class="a_black"
			href="javascript:orderAllConfirm(myForm)"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<button >전체 주문하기</button>
			<a class="a_black" 
			href="javascript:delAllCart(myForm)"> 전체 삭제하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <button id="delAllBtn" onclick="delAllCart(myForm)">전체 삭제하기</button> -->
			<button id="delAllCart" >전체 삭제하기</button>
			<a class="a_black" href="index.jsp"> 계속 쇼핑하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>
    