<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value="/css/header_styles.css" />" rel="stylesheet">
		<script src="<c:url value="/js/header_script.js" />"></script>
	</head>
	<body>
		<nav id="first-nav">
			<ul id="buttons">
				<li class="btn-li">
					<a class="btn-links" href="<cs:url value='/header/contacts'/>">
						<img src="<cs:url value='/img/buttons/contacts.jpg'/>" alt="contacts">
					</a>
				</li>
				<li class="btn-li">
					<a class="btn-links" href="<cs:url value='/header/home'/>">
						<img src="<cs:url value='/img/buttons/home.jpg'/>" alt="home">
					</a>
				</li>
				<li class="btn-li">
					<a class="btn-links" href="<cs:url value='/header/apple'/>">
						<img src="<cs:url value='/img/buttons/apple.jpg'/>" alt="apple">
					</a>
				</li>
				<li class="btn-li">
					<a class="btn-links" href="<cs:url value='/header/promo'/>">
						<img src="<cs:url value='/img/buttons/prom.jpg'/>" alt="promo">
					</a>
				</li>
				<li class="btn-li">
					<a class="btn-links" href="<cs:url value='/header/services'/>">
						<img src="<cs:url value='/img/buttons/services.jpg'/>" alt="services">
					</a>
				</li>
				<li class="btn-li">
					<a class="btn-links" href="<c:url value='/header/stores'/>">
						<img src="<cs:url value='/img/buttons/stores.jpg'/>" alt="stores">
					</a>
				</li>
			</ul>
		</nav>
		
		<div id="contaner">
		<div id="logo-search">
		<a href="index.jsp">
			<img id="logo" alt="technomarket_logo" src="<cs:url value='/img/logo/tm-logo.png'/>">
		</a>
		<div id="search-menu">
		<div id="search-con">
		<form id="search" action="<c:url value='/header/search'/>" method="get">
			<input type="text" id="searched-text" name="searched_text" size="40" maxlength="35" placeholder="Търси...">
			<input type="image" id="search-button" alt="Search" src="<cs:url value='/img/buttons/search.jpg'/>"><br>
		</form>
		</div>
		</div>
		<div id="menu-container">
		<div class="user_dropdown">
  			<button onclick="myFunction()" class="drop_head_button"><c:out value = "${sessionScope.user != null ? user.firstName : 'Вход'}"/></button>
  			<div id="dropdown-inner" class="dropdown_content">
   				<c:if test="${sessionScope.user == null}">
					<a href="<cs:url value='/header/login'/>">Вход</a>
   			 		<a href="<cs:url value='/header/register'/>">Регистрация</a>
				</c:if>
   				<c:if test="${sessionScope.user != null}">
					<a href="<cs:url value='/info/infoUserProfile'/>">Профил</a>
    				<a href="<cs:url value='/info/infoUserOrders'/>">Поръчки</a>
    				<a href="<cs:url value='/info/infoUserFavourites'/>">Любими</a>
    				<c:if test="${sessionScope.user.isAdmin == true}">
						<a href="<cs:url value='/info/infoAdminPanel'/>">Админ панел</a>
					</c:if>
					<a href="<cs:url value='logout'/>">Изход</a>
					</form>
				</c:if>
 			 </div>
 			 <button type="button"><c:out value = "${sessionScope.user.basket != null ? sessionScope.user.getBasketPrice() : '0'}"/></button>
		</div>
		</div>
		</div>
		</div>
	</body>
</html>