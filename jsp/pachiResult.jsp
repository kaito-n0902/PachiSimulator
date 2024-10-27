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

<%--過去ログの表示 --%>
<table border="1">
	<tr>
    	<th>機種</th>
        <th>投資金額</th>
        <th>初当たり回数</th>
        <c:choose>
        	<c:when test="${pachi.model == 'toloveru' }">
        		<th>900発</th>
        		<th>2100発</th>
        		<th>3300発</th>
        		<th>4500発</th>
        	</c:when>
        	<c:when test="${pachi.model == 'rezero' }">
        		<th>リスタート</th>
        		<th>300発</th>
        		<th>1500発</th>
        		<th>3000発</th>
        	</c:when>
        	<c:when test="${pachi.model == 'eva' }">
        		<th>1500発</th>
        	</c:when>
        </c:choose>
        <th>結果</th>
        <th>使用金額</th>
    </tr>
	<c:forEach var="pachiList" items="${pachiList}">
  	<tr>
		<td><c:out value="${pachiList.model}" /></td>
        <td><c:out value="${pachiList.money}" /></td>
        <td><c:out value="${pachiList.firstBonusCnt}" /></td>
        <c:choose>
        	<c:when test="${pachiList.model == 'toloveru' }">
        		<td><c:out value="${pachiList.petitBonus }" /></td>
        		<td><c:out value="${pachiList.mediumBonus }" /></td>
        		<td><c:out value="${pachiList.bigBonus }" /></td>
        		<td><c:out value="${pachiList.maxBonus }" /></td>
        	</c:when>
        	<c:when test="${pachiList.model == 'rezero' }">
        		<td><c:out value="${pachiList.petitBonus }" /></td>
        		<td><c:out value="${pachiList.mediumBonus }" /></td>
        		<td><c:out value="${pachiList.bigBonus }" /></td>
        		<td><c:out value="${pachiList.maxBonus }" /></td>
        	</c:when>
        	<c:when test="${pachiList.model == 'eva' }">
        		<td><c:out value="${pachiList.maxBonus }" /></td>
        	</c:when>
        </c:choose>
        <td><c:out value="${pachiList.ball }" /></td>
        <td><c:out value="${pachiList.usedMoney }" /></td>
	</tr>
    </c:forEach>
</table>
</body>
</html>