<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />



<jsp:include page="user_menu.jsp" />

<c:if test="${sessionScope.user == null}">
	<p>Само потребителите, влезли в сметката си, имат достъп до тази страница!</p>
</c:if>

<jsp:include page="footer.jsp" />
</body>
</html>