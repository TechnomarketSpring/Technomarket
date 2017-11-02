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



<jsp:include page="user_menu.jsp" />
  <c:if test="${sessionScope.user != null}">
    <c:if test="${product.size() == 0}">
	  <h4>${sessionScope.user.firstName} намя добавени продукти в категория любими!</h4>
    </c:if>
    <c:if test="${product != null && product.size() > 0}">
         <cs:forEach items="${ product }" var="favourite">
           <h4>Продукт* ${favourite.productNumber }</h4>
                <h4>Цена* ${favourite.price}</h4>
                <h4>Име* ${favourite.name}</h4>
                <h4>Номер на артикул* ${favourite.productNumber}</h4> 
                <h4>Гаранция* ${favourite.worranty}</h4>   
                <a class="btn-links" href="<c:url value='/info/infoForProduct?value=${favourite.productId}'/>">
							<img src="<c:url value='/product/product_pic?value=${favourite.productId}'/>" alt="product-image" width="120" height="auto">
				</a>
                <form action = "<c:url value='/favourite/removeFromFavorite'/>" method="post">
				      <input type = "submit" value = "Премахни продукта">
				      <input type = "hidden" name = "value" value = "${ favourite.productId }"/>
				    </form> 
   		 </cs:forEach>
     </c:if>
     </c:if>
       <c:if test="${sessionScope.user == null}">
				<p>Само потребителите, влезли в сметката си, имат достъп до тази страница!</p>
			</c:if>
<jsp:include page="footer.jsp" />
</body>
</html>