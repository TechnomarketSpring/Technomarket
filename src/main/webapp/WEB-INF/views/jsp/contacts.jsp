<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script src="<c:url value="/js/contacts_script.js" />"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<hr>

	<c:if test="${emptyEmail == true}">
			<p>Не сте въвели имейл! Моля променете полето преди да изпратите имейл!</p>
	</c:if>			
	<c:if test="${emptyMessage == true}">
			<p>Невалидно съобщение! Моля променете полето преди да изпратите имейл!</p>
	</c:if>	
	<c:if test="${invalidEmail == true}">
			<p>Невалиден имейл! Моля променете полето преди да изпратите имейл!</p>
	</c:if>	
	<c:if test="${invalidPhone == true}">
			<p>Невалиден телефонен номер! Моля променете полето преди да изпратите имейл!</p>
	</c:if>
	<c:if test="${emailSend == true}">
			<p>Съобщението е въведено спешно!</p>
	</c:if>	

  <form action="<c:url value="/info/contactsEmail" />" method  = "post">
    Име и Фамилия* <input type="text" name="names" required><br>
    Имайл* <input type="email" name="email" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required><br>
    Телефон* <input type="number" name="phone" pattern="[+]?[0-9]{1,10}" required><br>
    Съобщение*<textarea id="message" name="message" rows="4" cols="50" required></textarea>
   <input type = "submit" value = "Изпрати" onclick="isEmpty();">
   </form>
 </hr>
 <hr>
 <h3>Отдел електронна търговия</h3>

<h1><strong class="color-tm">0700 10 800</strong></h1>

<p><small>на цената на един градски разговор</small></p>

<hr/>
<hr>
<p><strong>Е-мейл:</strong> <a href="mailto:online@technomarket.bg">online@technomarket.bg</a></p>

<p><small class="color-gray">Моля, за предявяване на рекламация за уреди закупени On-Line моля използвайте: <a href="mailto:onlinereklamacii@technomarket.bg">onlinereklamacii@technomarket.bg</a></small></p>

 <h3>Отдел Корпоративни клиенти</h3>

<h1>&nbsp;</h1>

<p>&nbsp;</p>
<hr/>

<hr>
<address><strong>Е-мейл:</strong> <a href="mailto:corporate@technomarket.bg">corporate@technomarket.bg</a><br />
<strong><span class="title" style="height: 25px;">Адрес:</span></strong> гр.София п.к. 1138 бул.Цариградско шосе 361</address>

<hr>
<h3>Централен офис</h3>

<address><strong><span class="title" style="height: 25px;">Адрес:</span></strong> гр.София п.к. 1138 бул.&nbsp;Цариградско шосе 361<br />
<strong>Е-мейл:</strong> <a href="mailto:info@technomarket.bg">info@technomarket.bg</a><br />
Тел.: 02&nbsp;9421 101<br />
Тел.: 02 9421 105</address>
            </div>
        </div>
    </div>
</hr>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>