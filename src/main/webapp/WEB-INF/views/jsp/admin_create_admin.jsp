<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
			<jsp:include page="header.jsp" />
			<p>ÐÐ°Ð¿ÑÐ°Ð²Ð¸ Ð¿Ð¾ÑÑÐµÐ±Ð¸ÑÐµÐ» Ð°Ð´Ð¼Ð¸Ð½:</p>
			<form action="<cs:url value='/product/insert_product'/>" method="post" enctype="multipart/form-data">
				<label for="name">ÐÐ¼Ðµ</label>
				<input type="email" name="productName" value="name" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required><br>
				<input type="submit" value="ÐÐ¾Ð±Ð°Ð²Ð¸">
			</form>




			<jsp:include page="footer.jsp" />
	</body>
</html>