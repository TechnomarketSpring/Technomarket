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
       <c:if test = "${orders.size() != 0 }">
           <div style = "border:1px solid green">
              <h3>"${user.firstName}" няма направени поръчки!</h3>
           </div >
       </c:if>
 

  <c:if test="${orders != null}">
  <h3>Всички поръчки на ${user.firstName}</h3>
	 <tbody>
	  <c:forEach items="${orders}" var="userOrders">
  	 	
       <tr>
      		<td> Номер* ${userOrders.orderId}|</td> 
            <td> Дата* ${userOrders.time}|</td>
            <td> Статус* ${userOrders.isConfirmed == false ? "Непотвърдена" : "Потвърдена"}|</td> 
            <td align="right">
            <form action = "<cs:url value='/info/infoFoCurrentOrder'/>" method="post">
                <input type = "submit" value = "Виж поръчката">
                <input type="hidden" name="value" value = "${userOrders.orderId}">
            </form>
            </i></a></td>
        </tr><br>

           </tbody>			
</c:forEach>
  </c:if>
  			</c:if>
  <c:if test="${sessionScope.user == null}">
				<p>Само потребителите, влезли в сметката си, имат достъп до тази страница!</p>
			</c:if>
<jsp:include page="footer.jsp" />
</body>
</html>