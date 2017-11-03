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


<c:if test="${sessionScope.user != null}">
 <c:if test="${order!= null}">
	 
	 	<h4>Номер на поръчка* ${order.orderId}</h4>
	 	
  	 	   <c:forEach items = "${products}" var = "product" >
  	 	        <h4>Продукт* ${product.key.name}</h4>
  	 	        <c:if test="${product.key.percentPromo == 0}">
                <h4>Цена* ${product.key.price}</h4>
                </c:if>
                <c:if test="${product.key.percentPromo != 0}">
                  <h4>Стара цена* <del>${product.key.price}</del></h4>
				  <c:set var="promoPrice" value="${0}"/>
        		  <c:set var="promoPrice" value="${promoPrice + product.key.price - (product.key.price * product.key.percentPromo/100)}" />
          		  <h4>Промо цена* ${promoPrice}</h4>
                </c:if>
                <h4>Име* ${product.key.name}</h4>
                <h4>Гаранция* ${product.key.worranty}</h4>   
                <img src="<c:url value='/product/product_pic?value=${product.key.productId}'/>" alt="product-image" width="120" height="auto">
        	    <hr>
  	 	   </c:forEach>
  	 	   
  	 	   
  	 	   <c:set var="total" value="${0}"/>
  	 	   <c:forEach items = "${products}" var = "product" >
  	 	     <c:set var="total" value="${ total + (product.key.price*product.value) - (((product.key.price * product.key.percentPromo)/100)*product.value) }" />
  	 	   </c:forEach>
  	 	   <h4>Цената на поръчката е: <span id="priceSum">${total}</span> лв.</h4>
  	 	      
         				

  </c:if>
	</c:if>


	<c:if test="${sessionScope.user == null}">
				<p>Само потребителите, влезли в сметката си, имат достъп до тази страница!</p>
	</c:if>
<jsp:include page="footer.jsp" />

</body>
</html>