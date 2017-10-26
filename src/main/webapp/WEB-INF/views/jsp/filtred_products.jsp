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
		<jsp:include page="header.jsp"></jsp:include>
		<c:if test="${filtredProducts != null}">
			<c:forEach items="${filtredProducts}" var="filtredProduct">
				<div style="border:1px solid black;">
				
				<img src="${filtredProduct.imageUrl} "  width="120" height="auto">
				 <h5>Име на продукта* ${filtredProduct.name}</h5><br>
				 <h5>Гаранция* ${filtredProduct.worranty}</h5><br>
				<c:if test="${ filtredProduct.percentPromo > 1}">
				 <h5>Промоция* ${filtredProduct.percentPromo}</h5>
				</c:if>
				 <span>Номер на продукт* ${filtredProduct.productNumber}</span>
				<h5>Цена*${filtredProduct.price}</h5>
				<input type="button" value="Купи сега" name="${ filtredProduct.productId}">
				</div>			
			</c:forEach>
		</c:if>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>

