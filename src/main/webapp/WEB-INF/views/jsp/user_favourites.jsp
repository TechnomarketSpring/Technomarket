<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<p>Ð¡Ð°Ð¼Ð¾ Ð¿Ð¾ÑÑÐµÐ±Ð¸ÑÐµÐ»Ð¸ÑÐµ, Ð²Ð»ÐµÐ·Ð»Ð¸ Ð² ÑÐ¼ÐµÑÐºÐ°ÑÐ° ÑÐ¸, Ð¸Ð¼Ð°Ñ Ð´Ð¾ÑÑÑÐ¿ Ð´Ð¾ ÑÐ°Ð·Ð¸ ÑÑÑÐ°Ð½Ð¸ÑÐ°!</p>
</c:if>

<jsp:include page="footer.jsp" />
</body>
</html>