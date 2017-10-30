<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
	<title>Insert title here</title>
	<script src="<c:url value="/js/product_script.js" />"></script>
	<script type="text/javascript">
		
	
	
	
	</script>
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
					<form id="search" action="<c:url value='/product/setPromo'/>" method="post">
						<input type="hidden" name="productId" id="product" value="${product.productId}">
						<input type="number" id="promo-box" name="promoPercent" size="40" maxlength="35" min="0" value="0">
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
									<input type="number" class="quantity" id="amount${entry.key.storeId}" name="quantity" min="0" required>
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