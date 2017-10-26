<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<cs:url value='/js/common_scripts.js'/>"></script>
		<title>Insert title here</title>
	</head>
	<body>
	<jsp:include page="header.jsp" />
	  <h1>Вход в сайта</h1>	
	  
		<c:if test="${ invalidUser != null}">
			<div>
			<p>Невалидно удостоверение за автентикация.</p>
			</div>
		</c:if>
		<form action="<cs:url value='/login'/>" method="post">
			Е-мейл <input type="text" name="username"><br>
			Парола <input type="password" name="password"><br>
			Запомни ме <input type = "checkbox" name = "remember"> 
			<a href="forgotten">Забравена парола?</a>
			<input type="submit" value="Вход"><br>
		</form>
		<h1>Нямаш профил?</h1>
		<button onclick="visitRegister();">Регистрирай се</button>
		<jsp:include page="footer.jsp" />
	</body>
</html>