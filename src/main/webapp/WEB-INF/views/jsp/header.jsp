<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value="/css/header_slyles.css"/>" rel="stylesheet">
		<script src="<c:url value="/js/header_scripts.js"/>"></script>
	</head>
	<body>
		<nav>
			<ul>
				<li>
					<a href="<cs:url value='/header/contacts'/>">
						<img src="<cs:url value='/img/buttons/contacts.jpg'/>" alt="contacts">
					</a>
				</li>
				<li>
					<a href="<cs:url value='/header/home'/>">
						<img src="<cs:url value='/img/buttons/home.jpg'/>" alt="home">
					</a>
				</li>
				<li>
					<a href="<cs:url value='/header/apple'/>">
						<img src="<cs:url value='/img/buttons/apple.jpg'/>" alt="apple">
					</a>
				</li>
				<li>
					<a href="<cs:url value='/header/promo'/>">
						<img src="<cs:url value='/img/buttons/prom.jpg'/>" alt="promo">
					</a>
				</li>
				<li>
					<a href="<cs:url value='/header/services'/>">
						<img src="<cs:url value='/img/buttons/services.jpg'/>" alt="services">
					</a>
				</li>
				<li>
					<a href="<c:url value='/header/stores'/>">
						<img src="<cs:url value='/img/buttons/stores.jpg'/>" alt="stores">
					</a>
				</li>
			</ul>
		</nav>
		<a href="index.jsp">
			<img alt="technomarket_logo" src="D:\technomarket_images\logo\tm-logo.png">
		</a>
		<form action="<c:url value='/header/search'/>" method="get">
			<input type="text" name="searched_text" placeholder="Търси...">
			<input type="submit" id="search_button"><br>
		</form>
		<div class="user_dropdown">
  			<button class="drop_head_button"><c:out value = "${sessionScope.user != null ? user.firstName : 'Вход'}"/></button>
  			<div class="dropdown_content">
   				<c:if test="${sessionScope.user == null}">
					<a href="<cs:url value='/header/login'/>">Вход</a>
   			 		<a href="<cs:url value='/header/register'/>">Регистрация</a>
				</c:if>
   				<c:if test="${sessionScope.user != null}">
					<a href="profile.jsp">Профил</a>
    				<a href="orders.jsp">Поръчки</a>
    				<a href="favourites.jsp">Любими</a>
    				<c:if test="${sessionScope.user.isAdmin == true}">
						<a href="admin_panel.jsp">Админ панел</a>
					</c:if>
					<form action="logout" method="post">
						<a id="logout_a">Изход</a>
						<input type="submit" id="logout_submit"/>
					</form>
				</c:if>
 			 </div>
		</div>
		<button type="button"><c:out value = "${sessionScope.user.basket != null ? sessionScope.user.getBasketPrice() : '0'}"/></button>
	</body>
</html>