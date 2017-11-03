<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
	<title>Insert title here</title>
	<script src="<c:url value="/js/product_script.js" />"></script>
	<link href="<c:url value="/css/product_styles.css" />" rel="stylesheet">
</head>
	<body>
		<jsp:include page="header.jsp" />
		<div id="product-pic-and-title">
			<h3>${product.name}</h3>
			<h6>${product.productNumber}</h6>
			<a target="_blank" href="<c:url value='/product/product_pic?value=${product.productId}'/>">
				<img id="picture-nail" src="<c:url value='/product/product_pic?value=${product.productId}'/>" alt="product-image" width="150" height="auto">
			</a>
		</div>
		
		<c:if test="${sessionScope.user.isAdmin == true}">
		<c:if test="${invalidPercent == false }">
			<p>Невалиден запис! Позволените проценти са от 0 до 99!</p>
		</c:if>
		<ul id="admin-buttons">
				<li class="admin-btn">
					<form id="search" action="<c:url value='/product/setPromo'/>" method="post">
						<input type="hidden" name="productId" id="product" value="${product.productId}">
						<input type="number" id="promo-box" name="promoPercent" size="40" min="0" max="99" value="0" onclick="return empty();" required>
						<input type="image" id="grant-promo" alt="set-promo" src="<c:url value='/img/buttons/admin-buttons/grant-promo.png'/>"><br>
					</form>
				</li>
				<li class="admin-btn">
					<a class="btn-links" href="<c:url value='/product/remove?value=${product.productId}'/>">
						<img src="<c:url value='/img/buttons/admin-buttons/delete.png'/>" alt="delete">
					</a>
				</li>
		</ul>		
		</c:if>
		
		<div id="product-info">
		<p>${product.name}</p>
		<p>${product.tradeMark}</p>
		<c:if test="${sessionScope.user != null}">
		 <c:if test="${isProductInFavourite == false}">
			<form action = "<c:url value='/favourite/addInFavorite'/>" method = "get">
				<input type = "hidden" name = "value" value = "${ product.productId }"/>
				<input type = "submit" value = "Добави в любими"/>
			</form>
		</c:if>
		<c:if test="${isProductInFavourite == true}">
		<form action = "<c:url value='/favourite/removeFromFavorite'/>" method="post">
			<input type = "submit" value = "Премахни от любими">
			<input type = "hidden" name = "value" value = "${ product.productId }"/>
		</form> 
		</c:if>
		</c:if>
		<!-- TODO add description of product here!!! -->
		</div>
		
		
		<div id="buy-table">
		<c:if test="${product.percentPromo > 0}">
			<img alt="promo-sticker" src="<c:url value='/img/stickers/promo.jpg'/>">
		</c:if>
		<c:if test="${product.isNewProduct == true}">
			<img alt="new-sticker" src="<c:url value='/img/stickers/new.jpg'/>">
		</c:if>
		
		
		<tr>
			<c:if test="${product.percentPromo == 0}">
				<td><h1>Цена: ${product.price}</h1></td>
			</c:if>
			<c:if test="${product.percentPromo > 0}">
				<c:set var="promoPrice" value="${0}"/>
				<c:set var="promoPrice" value="${promoPrice + product.price - (product.price * product.percentPromo/100)}" />
				<td><h1>Стара цена: <del>${product.price}</del></h1></td>
				<td><h1>Промо цена: ${promoPrice}</h1></td>
			</c:if>
			<td></td>
			<td><div>${product.worranty}</div></td>
			<td>
				<c:if test="${isProductInStock == true }">
					<form action = "<c:url value='/buyController/buy'/>" method = "post">
					  <input type = "hidden" name = "value" value = "${product.productId}"/>
					  <input type = "image" id="buy-button" alt="buy-button" src="<c:url value='/img/buttons/buy-buttons/buy_online.png'/>"/>
					</form>	
				</c:if>
				<c:if test="${isProductInStock == false }">	
					<a class="btn-links" href="<c:url value='/info/infoContacts'/>">
						<img src="<c:url value='/img/buttons/buy-buttons/request.png'/>" alt="request">
					</a>
					<span>Продуктът не е в наличност!</span><br>
					<span>В случай, че проявявате интерест, ще получите контакти за заявка.</span>
				</c:if>
				
		<!-- 	<button><a class="btn-links" href="<c:url value='/buyController/buy?value=${product.productId}'/>">Купи сега</a></button> -->
		</td>
		</tr>
		</div>
		
		<c:if test="${isProductInStock == true}">
			<img alt="status-legend" src="<c:url value='/img/legends/legend.png'/>">
		</c:if>
		<c:if test="${isProductInStock == false}">
			<img alt="status-legend" src="<c:url value='/img/legends/no_product.png'/>">
		</c:if>
		<div id="stores">
			<c:forEach items="${statusPerStore}" var="entry">
				<ul>
					<c:if test="${(sessionScope.user.isAdmin == false && entry.value != null) || (sessionScope.user.isAdmin == true) || (sessionScope.user == null && entry.value != null)}">
						<li><strong>${entry.key.city}</strong>, ${entry.key.address} 
							<c:if test="${entry.value != null }">
								<img alt="status" src="<c:url value='${entry.value}'/>">
							</c:if>
							<c:if test="${sessionScope.user.isAdmin == true}">
							<!-- 	<form action="/product/changeQuantityPerStore" method="post"">  -->
									<label for="quantity">Смени количество: </label>
									<input type="hidden" name="productId" id="product" value="${product.productId}">
									<input type="hidden" name="storeId" id="store${entry.key.storeId}" value="${entry.key.storeId}">
									<input type="number" class="quantity" id="amount${entry.key.storeId}" name="quantity" min="0" max = "1000" onclick="return empty();" required>
									<input type="submit" id="ajax-submit" onclick="submitQuery(${entry.key.storeId})" value="Запиши">
							<!-- </form> --><br>
							</c:if>
						</li>
					</c:if>
				</ul>
        	    </c:forEach>		
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>