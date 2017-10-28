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
		<div id="product-info">
		<p>${product.name}</p>
		<p>${product.tradeMark}</p>
		</div>
		<div id="buy-table">
		<tr>
			<td><h1>${product.price}</h1></td>
			<td><div>${product.worranty}</div></td>
			<td><bitton><a class="btn-links" href="<c:url value='/buyController/buy?value=${filtredProduct.productId}'/>">Купи сега</a></bitton></td>
		</tr>
		</div>
		<div id="stores">
		
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>