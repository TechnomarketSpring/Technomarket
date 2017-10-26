<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/user_menu.css" />" rel="stylesheet">
</head>
<body>
	<c:if test="${sessionScope.user != null}">
		<ul>
			<li id="head"><stong><span id="glyphicons-parents"></span>Профил</stong></li>
			<li class="menu-button"><a href="${pageContext.request.contextPath}/info/infoUserProfile">> Моят профил</a></li>
			<li class="menu-button"><a href="${pageContext.request.contextPath}/info/infoUserOrders">> Моите поръчки</a></li>
			<li class="menu-button"><a href="${pageContext.request.contextPath}/info/infoUserFavourites">> Любими продукти</a></li>
				<c:if test="${session.user.isAdmin == true}">
					<li class="menu-button"><a href="${pageContext.request.contextPath}/info/infoAdminPanel">> Админ панел</a></li>
				</c:if>
			<li class="menu-button"><a href="logout">> Изход</a></li>
		</ul>
	</c:if>
	<c:if test="${sessionScope.user == null}">
	
	</c:if>
</body>
</html>