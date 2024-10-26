<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PachiResult</title>
</head>
<body>
<p>収支</p>
選んだ台：<c:out value="${pachi.model}" /><br>
投資金額：<c:out value="${pachi.money}" />円<br>
初当たり回数：<c:out value="${pachi.firstBonusCnt}" /><br>
ボーナス回数：
<c:choose>
	<c:when test="${pachi.model == 'toloveru'}">
		900発 <c:out value="${pachi.petitBonus }"/> 回, 
		2100発 <c:out value="${pachi.mediumBonus }"/> 回, 
		3300発 <c:out value="${pachi.bigBonus }"/> 回, 
		4500発 <c:out value="${pachi.maxBonus }"/> 回
	</c:when>
	<c:when test="${pachi.model == 'rezero' }">
		リスタート <c:out value="${pachi.petitBonus }"/> 回, 
		300発 <c:out value="${pachi.mediumBonus }"/> 回, 
		1500発 <c:out value="${pachi.bigBonus }"/> 回, 
		3000発 <c:out value="${pachi.maxBonus }"/> 回
	</c:when>
	<c:when test="${pachi.model == 'eva' }">
		1500発 <c:out value="${pachi.maxBonus }" /> 回
	</c:when>
</c:choose><br>
結果：<c:out value="${pachi.ball}" />発獲得<br>
使用金額：<c:out value="${pachi.usedMoney}" />円<br><br>
<a href="PachiTest">戻る</a>
<form action="PachiTest" method="post">
	<input type="hidden" name="radiobtn" value="${pachi.model }">
	<input type="hidden" name="money" value="${pachi.money }">
	<input type="submit" value="もう一度">
</form>
</body>
</html>