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
 <c:if test="${orders != null}">
	 <tbody>
	  <c:forEach items="${orders}" var="Orders">
  	 	<c:forEach items = "${orders.product}" var = "product" >
  	 	<tr>
  	 	<h2>Продукт*</h2>
            <td>Цена* product.price</td>
            <td>Име* product.name</td>
            <td>Номер на артикул* product.productNumber</td> 
             <td>Гаранция* product.worranty</td> 
           <td><img sr =product.imageUrl></td>   
        </tr>
        	<hr>
       
  	 	</c:forEach>
       <tr>
            <td>Цена* userOrders.price</td>
            <td>Дата* userOrders.time</td>
            <td>Статус* userOrders.isConfirmed</td> 
            <td align="right"><a href="<cs:url value='/info/infoForCurrentProduct?value=${userOrders}'/>">Виж поръчката <i class="icon-arrow-long-right"></i></a></td>
        </tr>

           </tbody>
           				
</c:forEach>
  </c:if>





<jsp:include page="footer.jsp" />

</body>
</html>