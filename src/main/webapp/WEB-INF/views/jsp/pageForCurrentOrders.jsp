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
 <c:if test="${order!= null}">
	 
	 <h4>Номер на поръчка* ${order.orderId}</h4>
  	 	   <c:forEach items = "${products}" var = "product" >
  	 	        
  	 	        <h4>Продукт* ${product.productNumber }</h4>
                <h4>Цена* ${product.price}</h4>
                <h4>Име* ${product.name}</h4>
                <h4>Номер на артикул* ${product.productNumber}</h4> 
                <h4>Гаранция* ${product.worranty}</h4>   
                <img src="<c:url value='/product/product_pic?value=${product.imageUrl}'/>" alt="product-image" width="120" height="auto">
          
        	    <hr>
  	 	   </c:forEach>
         				

  </c:if>





<jsp:include page="footer.jsp" />

</body>
</html>