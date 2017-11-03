<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Продукти на промоция¸</title>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		
		<c:if test="${ errorPrice != null}">
		  <h4>Невалидни данни за търсене по цена</h4>
		</c:if>
		
		<div>
		<h4>Търси продукти с цена между:</h4>
		<form action="<c:url value='/product/productByPrice'/> " method="get">
		от* <input type="text" id="number" name="price1" min = "0" max="99999" required >
		 до*<input type="text" id="number" name="price2" min="0" max="99999" required >
		 <input type = "submit">
		</form>
		</div>  
		
		
		<!--<form <c:url value='/header/search'/>" method="get">-->
		<form action="<c:url value='/product/compareProduct?compare=${mark}'/> " method="get">
		Сортирай по *<select name  = "compare" >
            <option value = "price">По ценa</option>
            <option value = "mark"  >По марка</option>
            <option value = "type">Промоциий</option>
        </select>
        <input type = "hidden" value = "${categoryName}" name = "categoryName">
        <input type = "submit">
        
        </form>

			<c:forEach items="${filtredProducts}" var="filtredProduct">
				<div id="product-box" style="border:1px solid black;">
					<div>
						<a class="btn-links" href="<c:url value='/info/infoForProduct?value=${filtredProduct.productId}'/>">
							<img src="<c:url value='/product/product_pic?value=${filtredProduct.productId}'/>" alt="product-image" width="120" height="auto">
						</a>
					 	<h5>${filtredProduct.name}</h5><br>
						<c:if test="${ filtredProduct.percentPromo > 0}">
						 	<img alt="promo-sticker" src="<c:url value='/img/stickers/promo.jpg'/>">
						</c:if>
						<c:if test="${ filtredProduct.isNewProduct == true}">
						 	<img alt="new-sticker" src="<c:url value='/img/stickers/new.jpg'/>">
						</c:if>
					 	<span>Арт. Nº: ${filtredProduct.productNumber}</span>
						<h5>${filtredProduct.price} лв.</h5>
					</div>
					
		<!--		<button><a class="btn-links" href="<c:url value='/buyController/buy?value=${filtredProduct.productId}'/>">Купи сега</a></button> -->
				</div>
				
				<form action = "<c:url value='/buyController/buy'/>" method = "post">
				  <input type = "hidden" name = "value" value = "${ filtredProduct.productId }"/>
				  <input type = "submit" value = "Купи сега"/>
				</form>		
			</c:forEach>

		<!--</form>-->
		<c:if test = "${filtredProducts.size() == 0}">
		      <div style = "border:1px solid red">
		     <h3>Няма намерен резутат от търсенето</h3>
		     </div>
		</c:if>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>

