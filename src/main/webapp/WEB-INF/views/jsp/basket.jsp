<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="<c:url value="/js/basket_scripts.js" />"></script>
	<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="border:1px solid green;">
           
          <h1>Кошница</h1>
          
          <c:if test="${sessionScope.basket.size() == 0}">
              <h4>Няма продукти във вашата кошница!</h4>
          </c:if>
          
          <c:if test="${sessionScope.basket.isEmpty() == false}">
              <c:forEach items="${sessionScope.basket}" var="entry">
                    <h5>Модел: ${entry.key.name}</h5>
                    <h5>Марка: ${entry.key.tradeMark}</h5>
                    <c:if test="${entry.key.percentPromo == 0}">
                   		<h5>Цена: ${entry.key.price}</h5>
                    </c:if>
                    <c:set var="counting" value="${entry.key.price}"/>
                    <c:if test="${entry.key.percentPromo > 0}">
							<c:set var="counting" value="${(counting + ((entry.key.price * pro.value)) - ((entry.key.price*entry.key.percentPromo)/100))}" />
                   		<h5>Стара цена: <del>${entry.key.price}</del></h5>
                   		<h5>Промо цена: ${counting}</h5>
                    </c:if>
                    
                    
                    </h5>
                    <h5>Артикул номер: ${entry.key.productNumber}</h5>
                    <h5>Гаранция: ${entry.key.worranty}</h5>
                    <h5>Отстъпка: ${entry.key.percentPromo}</h5>
                    <h5>Количесво:<input type="number" id="product${entry.key.productId}" min="0" value="${entry.value}" onchange="changeQuantity(${entry.key.productId});" onload="keepSame(${entry.key.productId});" onbeforeunload ="return false;"></h5><br> 
                    <form action = "<c:url value='/buyController/removeProduct?value=${entry.key.productId}'/>" method="post">
				      <input type = "submit" value = "Премахни продукта">
				    </form>  
              </c:forEach>
              <li class="btn-li">
					 <a class="btn-links" href="<cs:url value='/buyController/makeOrder'/>">
						<img src="<cs:url value='/img/buttons/deliver.png'/>" alt="contacts">
				   	</a>
			  </li>
			</div>
				<button><a class="btn-links" href="<c:url value='/header/goIndex'/>">Добаване на още продукти</a></button>
				
				</div>	
           </c:if> 
           <c:set var="total" value="${0}"/>
 		   	 <cs:forEach items="${ sessionScope.basket }" var="basket">
				 <c:set var="total" value="${(total + ((basket.key.price * basket.value)) - ((basket.key.price*basket.key.percentPromo)/100))}" />
   			 </cs:forEach>
   			 <c:if test="${total != 0}">
 			  <h4>Сумата за плащане е: <span id="priceSum">${total}</span> лв.</h4>
 			 </c:if>	
          
        
 		</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>