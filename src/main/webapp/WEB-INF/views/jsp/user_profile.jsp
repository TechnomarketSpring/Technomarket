<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<div>
		<jsp:include page="header.jsp" />
			<c:if test="${sessionScope.user != null}">
				<strong>Имейл:</strong> <span>"${ sessionScope.user.email }"</span> </br>
				<strong>Имейл:</strong> <span>"${ sessionScope.user.email }"</span> 
				<jsp:include page="user_menu.jsp" />
			</c:if>
			<c:if test="${sessionScope.user == null}">
				<p>Само потребителите, влезли в сметката си, имат достъп до тази страница!</p>
			</c:if>
		<jsp:include page="footer.jsp" />
		</div>
</body>
</html>