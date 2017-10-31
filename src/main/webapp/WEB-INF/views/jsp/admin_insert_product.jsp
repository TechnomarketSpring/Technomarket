<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<jsp:include page="header.jsp" />
		<c:if test="${sessionScope.user.isAdmin == true}">
		<cs:if test="${ added != null }">
			<div>
				<a href="<cs:url value='/info/infoForProduct?value=${productId}'/>">     
					<img id="admin-go" alt="go-button" src="<cs:url value='/img/buttons/admin-buttons/go.png'/>">
				</a>
				<p>Продуктът беше успешно добавен!<br>
				Моля преминете към страницата му, за да добавите количества по магазини</p>
				
			</div>
		</cs:if>
			<form action="<cs:url value='/product/insert_product'/>" method="post" enctype="multipart/form-data">
				<label for="name">Име на продукт</label>
				<input type="text" id="name" name="productName" required><br>
				<label for="trade-mark">Марка</label>
				<input type="text" id="trade-mark" name="tradeMark" required><br>
				<label for="category">Категория</label>
				<input type="text" id="category" name="categoryName" required><br>
				<label for="price">Цена</label>
				<input type="number" id="price" name="price" value="price" min="0" step="any" required><br>
				<label for="warranty">Гаранция в месеци</label>
				<input type="number" id="warranty" name="warranty" value="warranty" min="0" pattern= "[0-9]" required><br>
				<label for="promo">Промоционален процент</label>
				<input type="number" id="promo" name="promoPercent" value="promo" min="0" pattern= "[0-9]" required><br>
				<label for="image">Снимка на продукта<sup><a href="#fn1" id="ref1">*</a></sup></label>
				<input type="file" id="image" name="image" value="image" accept=".jpg,.png,.gif,.bmp,.jpeg" required>
				<input type="submit" value="Добави">
			</form>
	<sup id="fn1">1. Допустимите формати са: .jpg,.png,.gif,.bmp,.jpeg<a href="#ref1" title="Jump back to footnote 1 in the text.">↩</a></sup>
	</c:if>
	<c:if test="${sessionScope.user.isAdmin == false}">
		<p>Само администраторите, влезли в сметката си, имат достъп до тази страница!</p>
	</c:if>
	<jsp:include page="footer.jsp" />
	</body>
</html>