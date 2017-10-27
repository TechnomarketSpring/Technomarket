<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/css/admin_panel.css' />" rel="stylesheet">
<title>Insert title here</title>
</head>
	<body>
		<jsp:include page="header.jsp" />
			<c:if test="${sessionScope.user.isAdmin == true}">
				<h1>Администраторски инструменти:</h1>
				<jsp:include page="user_menu.jsp" />
				<div id="admin-btns">	
					<a class="btn" href="<c:url value='/product/insert_product' />"><span class="btn-text"><strong>Нов продукт</strong></span></a>
					<a class="btn" href="<c:url value='/info/infoAdminCreateAdmin' />"><span class="btn-text"><strong>Нов админ</strong></span></a>
					<a class="btn" href="<c:url value='/info/infoUserBan' />"><span class="btn-text"><strong>Бан секира</strong></span></a>
					<a class="btn" href="<c:url value='/info/infoAdminOrders' />"><span class="btn-text"><strong>Поръчки</strong></span></a>
				</div>
			</c:if>
		<jsp:include page="footer.jsp" />
		<c:if test="${sessionScope.user.isAdmin == false}">
			<p>Само администраторите, влезли в сметката си, имат достъп до тази страница!</p>
		</c:if>				
	</body>
</html>