<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
    <jsp:include page="header.jsp" />
		<c:if test="${sessionScope.user.isAdmin == true}">
		  <divstyle="border:1px solid black";>
		    <cs:forEach items="${ orders }" var="order">
		    
               <h4> Дата* ${order.time}|</h4>
               <h4> Статус* ${order.isConfirmed == false ? "Непотвърдена" : "Потвърдена"}|<h4> 
               <h4> Име на Потребител* ${order.userNames}|</h4>
               <h4> Тел. на Потребител* ${order.userPhoneNumber}|</h4>
                 <c:if test="${order.isConfirmed == false}">
                      <form action = "<cs:url value='/admin/confirmed'/>" method="post">
                      <input type = "submit" value = "Потвърди поръчка">
                      <input type="hidden" name="value" value = "${order.orderId}">
                  </c:if>
                   </form>
                      <c:if test="${order.isConfirmed == true}">
                         <form action = "<cs:url value='/admin/setPaid'/>" method="post">
                         <input type = "submit" value = "Заплащане">
                         <input type="hidden" name="value" value = "${order.orderId}">
                      </form>
                     </c:if>
                       <c:if test="${order.isConfirmed == true}">
                           <form action = "<cs:url value='/admin/removeOrder'/>" method="post">
                           <input type = "submit" value = "Премахване от потвърдени">
                           <input type="hidden" name="value" value = "${order.orderId}">
                       </c:if>
                    </div>
                 
            </cs:forEach>
		</c:if>
		<c:if test="${sessionScope.user.isAdmin == false}">
			<p>Само администраторите, влезли в сметката си, имат достъп до тази страница!</p>
		</c:if>
		    <jsp:include page="footer.jsp" />
	</body>
</html>