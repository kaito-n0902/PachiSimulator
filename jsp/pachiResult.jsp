<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Pachi" %>
<%
Pachi pachi = (Pachi)request.getAttribute("pachi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PachiResult</title>
</head>
<body>
<p>収支</p>
選んだ台：<%= pachi.getModel() %><br>
投資金額：<%= pachi.getMoney() %>円<br>
初当たり回数：<%= pachi.getFirstBonusCnt() %><br>
ボーナス回数：<%
if(pachi.getModel().equals("toloveru")){
	out.println("900発：" + pachi.getPetitBonus() + "回, ");
	out.println("2100発：" + pachi.getMediumBonus() + "回, ");
	out.println("3300発：" + pachi.getBigBonus() + "回, ");
	out.println("4500発：" + pachi.getMaxBonus() + "回");
}else if(pachi.getModel().equals("rezero")){
	out.println("リスタート：" + pachi.getPetitBonus() + "回, ");
	out.println("300発：" + pachi.getMediumBonus() + "回, ");
	out.println("1500発：" + pachi.getBigBonus() + "回, ");
	out.println("3000発：" + pachi.getMaxBonus() + "回");
}else if(pachi.getModel().equals("eva")){
	out.println("1500発：" + pachi.getMaxBonus() + "回");
}
%><br>
結果：<%= pachi.getBall() %>発獲得<br>
使用金額：<%= pachi.getUsedMoney() %>円<br><br>
<a href="PachiTest">戻る</a>
</body>
</html>