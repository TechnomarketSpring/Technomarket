<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
			<jsp:include page="header.jsp" />

		<c:if test="${sessionScope.user.isAdmin == true}">
			<p>Въведете имейла на потребителя, който ще получи администраторки права:</p>
			<form action="<cs:url value='/product/insert_product'/>" method="post" enctype="multipart/form-data">
				<label for="name">Имейл</label>
				<input type="email" name="productName" value="name" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required><br>
				<input type="submit" value="Въведи">
			</form>
		</c:if>
		<c:if test="${sessionScope.user.isAdmin == false}">
			<p>Само администраторите, влезли в сметката си, имат достъп до тази страница!</p>
		</c:if>


			<jsp:include page="footer.jsp" />
	</body>
</html>