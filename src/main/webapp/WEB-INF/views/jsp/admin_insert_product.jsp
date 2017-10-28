<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<jsp:include page="header.jsp" />
		<c:if test="${ added != null }">
			<p>Продуктът беше успешно добавен!</p>
		</c:if>
			<form action="<cs:url value='/product/insert_product'/>" method="post" enctype="multipart/form-data">
				<label for="name">Име</label>
				<input type="text" name="productName" value="name" required><br>
				<label for="trade-mark">Марка</label>
				<input type="text" name="tradeMark" value="name" required><br>
				<label for="category">Категория</label>
				<input type="text" name="categoryName" value="name" required><br>
				<label for="price">Цена</label>
				<input type="number" name="price" value="price" min="0" required><br>
				<label for="warranty">Гаранция в месеци</label>
				<input type="number" name="warranty" value="warranty" min="0" required><br>
				<label for="promo">Промоционален процент</label>
				<input type="number" name="promoPercent" value="promo" min="0" required><br>
				<label for="image">Снимка на продукта<sup><a href="#fn1" id="ref1">*</a></sup></label>
				<input type="file" name="image" value="image" accept=".jpg,.png,.gif,.bmp,.jpeg" required>
				<input type="submit" value="Добави">
			</form>
	<sup id="fn1">1. Допустимите формати са: .jpg,.png,.gif,.bmp,.jpeg<a href="#ref1" title="Jump back to footnote 1 in the text.">↩</a></sup>
	<jsp:include page="footer.jsp" />
	</body>
</html>