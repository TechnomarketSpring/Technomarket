<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cs" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
	<h1>Регистрация</h1>
	<c:if test="${dataError != null }">
			<div>
			<p>Данните за регистрация не са валидни!</p>
		</div>
	</c:if>
	<c:if test="${ date != null }">
		<div>
			<p>Данните за регистрация не са валидни!</p>
		</div>
	</c:if>
	<c:if test="${ passError != null }">
		<div>
			<p>Паролите не съвпадат!</p>
		</div>
	</c:if>
	<c:if test="${ emailError != null }">
		<div>
			<p>Имейл адреса вече съществува!</p>
		</div>
	</c:if>
	<c:if test="${ submitError != null }">
		<div>
			<p>Трябва да приемете условията за да продължите!</p>
		</div>
	</c:if>
		<c:if test="${ invlidPassError != null }">
		<div>
			<p>Паролата трябва да съдържа поне 8 символа от тип букви и цифри едновременно като кирилицата не е разрешена!</p>
		</div>
	</c:if>
		<c:if test="${ invlidMail != null }">
		<div>
			<p>Невалиден имейл!</p>
		</div>
	</c:if>
		<form action="register" method="post">
				Име* <input type="text" name="firstName" maxlength="35" required><br>
				Фамилия* <input type="text" name="lastName" maxlength="35" required><br>
				Имайл* <input type="email" name="email" maxlength="35" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required><br>
				Парола* <input type="password" name="password" maxlength="35" required></input><br>
				Парола(отново)* <input type="password" maxlength="35" name="password1" required></input><br>
				Пол* <input type="radio" name="gender" value="male">Мъж <input
				type="radio" name="gender" value="female">Жена<br>
				Датa на раждане* <input type="date" name="bday" max="2017-11-03" required><br>
				<input type="checkbox" name="abonat" value="1">Искам да получавам ингормация за промоционални и нови продукти.<br> 
				<input type="checkbox" name="submit" value="1">Приемам условията за позлване<br> 
				<input type="submit" value="Регистация">
		</form>
	<jsp:include page="footer.jsp" />
</body>
</html>