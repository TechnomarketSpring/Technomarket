<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<jsp:include page="header.jsp" />
		<p>Моля, запишете наличните количества за магазините, които желаете:</p>
		<div>
			<c:if test="${added == true} }">
				<p>Количеството е записано успешно!</p>
			</c:if>
			<form action="/product/importNewQuantity" method="post">
 				<c:forEach items="${storesPerCity}" var="entry">
 					<span><strong>${entry.key}:</strong></span><br>
 					<label for="quantity">${entry.value}</label>
 					<input type="number" id="quantity" name="${entry.value}" min="0">
        	    </c:forEach>
            <input type="submit" value="Въведи"/>
            </form>
		</div>



		<jsp:include page="footer.jsp" />
	</body>
</html>