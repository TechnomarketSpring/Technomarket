<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="log-head">
<head>
		<title>Техномаркет - магазини</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${ stores != null}">
<div style="border:1px solid green;">
			<c:forEach items="${stores}" var="store">
				<div>
				<h5>Адрес* ${store.address}</h5>
				<h5>Тел. номер* ${store.phoneNumber}</h5>
				<h5>Имейл* ${store.email}</h5>
				<h5>Работно време* ${store.workingTime}</h5>
				<h5>GPS* ${store.gps}</h5>
				<img src="${ store.storeImageUrl} "  width="120" height="auto">
				<form action = "<c:url value='/store/showInTheMap'/>" method = "get">
				  <input type = "hidden" name = "value" value = "${store.storeId}"/>
				  <input type = "submit" value = "Google map"/>
				</form>
				</div>			
			</c:forEach>
		</c:if>
		</div>
<jsp:include page="footer.jsp" />
</body>
</html>