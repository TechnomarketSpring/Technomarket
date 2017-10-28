<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<jsp:include page="header.jsp" />
		<div id="product-pic-and-title">
			<h3>${product.name}</h3>
			<h6>${product.productNumber}</h6>
			<img src="<c:url value='/product/product_pic?value=${product.productId}'/>" alt="product-image" width="120" height="auto">
		</div>
		
		<c:if test="${sessionScope.user.isAdmin == true}">
		<ul id="admin-buttons">
				<li class="admin-btn">
					<a class="btn-links" href="<cs:url value='/header/contacts'/>">
						<img src="<c:url value='/img/buttons/admin-buttons/grant-promo.png'/>" alt="grant-promo">
					</a>
				</li>
				<li class="admin-btn">
					<a class="btn-links" href="<c:url value='/product/changeQuantityPerStore'/>">
						<img src="<c:url value='/img/buttons/admin-buttons/change-quantity.png'/>" alt="change-quantity">
					</a>
				</li>
				<li class="admin-btn">
					<a class="btn-links" href="<cs:url value='/header/contacts'/>">
						<img src="<c:url value='/img/buttons/admin-buttons/delete.png'/>" alt="delete">
					</a>
				</li>
		</ul>		
		</c:if>
		
		<div id="product-info">
		<p>${product.name}</p>
		<p>${product.tradeMark}</p>
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
			<td><h1>${product.price}</h1></td>
			<td><div>${product.worranty}</div></td>
			<td><button><a class="btn-links" href="<c:url value='/buyController/buy?value=${filtredProduct.productId}'/>">Купи сега</a></button></td>
		</tr>
		</div>
		
		
		
		<div id="stores">
			
			
			
			
			
			
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>