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
    <div style="border:1px solid red";>
         <h3><a href="">Вашата поръчка все още не е потвърдена</a></h3>
    </div>
            <div>
                <dl>
                    <dt>Дата:</dt><dd><p>${ date }</p></dd>
                    <dt>Статус:</dt><dd><p>Непотвърдена поръчка</p></dd>
                    <dt>Е-майл:</dt><dd><p><a href="">${ sessionScope.user.email }</a></p></dd>
                </dl>
            </div>
            
     <div style="border:1px solid red";>
         <h3><a href="">Детайли на поръчката</a></h3>
    </div>
    <cs:forEach items="${ sessionScope.basket }" var="basket">
        <div style="border:1px solid red";>
        <h5>${basket.value} X ${basket.key.name} - <span>price:  ${basket.key.price} * ${basket.value}</span></h5>
        <h5>Доставка -</h5>
       </div>
    </cs:forEach>
    <div style = "border:1px solid red">
        <h4>Общо за плащане: ${price}</h4>
    </div>
     <div style = "border:1px solid red">
                <h3>Адрес за доставка</h3>
     </div>
     <div>
     <form action="<cs:url value='/buyController/addOrder'/>" method="post">
				Име и Фамилия* <input type="text" name="firstAndLastName" required><br>
				Телефон* <input type="number" name="telNumber" pattern="/^08[7-9][0-9]{7}$/" required><br>
				Град* <input type="text" name="town" required></input><br>
				Пощенски код* <input type="number" name="postCode" min = 4 pattern= "[0-9]" required></input><br>
				Улица/Квартал* <input type="text" name="street" required>  Номер*
				<input type="number" name="number" required><br>
				Блок*<input type="text" name="block">  
                Вход*<input type="text" name="entrace"> 
                Етаж*<input type="text" name="floor"> 
                Апартамент*<input type="text" name="aparment"><br>
                Уточнения за адрес*<input type="text" name="notes"><br>
                Начин на плащане*<select name = "payment">
                    <option value = "buy-shipping">При получаване</option>
                    <option value="banckCard">Банкова карта</option>
                </select>
					<input type="hidden" name="price" value = "${price}">
					<input type="submit" value="Изпрати">
		</form>
     </div>
    <jsp:include page="footer.jsp" />
</body>
</html>