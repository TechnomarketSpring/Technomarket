<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
<body>
<div id="main-container" style="border:1px solid red;">
<ul>
<h4>Техномаркет</h4>
<li> <a href="<cs:url value='/header/stores'/>">Магазини</a> </li>
<li> <a href="contacts">Контакти</a> </li>
</ul>

<ul>
<h4>Лесно пазаруване</h4>      
<li> <a href="<cs:url value='/info/infoForShoppingCon'/>">Успловия за пазаруване</a> </li>
<li> <a href="<cs:url value='/info/infoForDelivery'/>">Бърза и безплатна доставка до дома Ви</a> </li>
<li> <a href="<cs:url value='/info/infoForOnlinePay'/>/">Сигурно Online разплащане</a> </li>
<li> <a href="<cs:url value='/info/infoForTBICredit'/>">Условия за TBI Bank кредитиране</a> </li>
<li> <a href="<cs:url value='/info/infoForUniCredit'/>">Условия за UniCredit кредитиране</a> </li>
</ul>

<ul>
<h4>Контакти</h4><br>
<h3>Национален телефон на клиента</h3>
<h3 >0700 10 800<h3>
<h5>на цената на един градски разговор<h5>
</ul>
</div>
</body>
</html>