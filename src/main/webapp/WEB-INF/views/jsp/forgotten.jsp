<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<jsp:include page="header.jsp" />
		<h1>Възтоновяване на парола</h1>

        <c:if test="${ emailError != null }">
			<div>
			<p>Невалиден потребител</p>
			</div>
		</c:if>
		<form action="forgotten" method="post">
		Е-мейл <input type="email" name="email"><br>
		<input type="submit" value="Изпрати"><br>
		</form>
		<jsp:include page="footer.jsp" />
</body>
</html>