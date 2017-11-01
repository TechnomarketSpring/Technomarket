<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <jsp:include page="header.jsp"/>
		<div>
				<c:if test="${ systemProblem != null }">
					<p>В момента системата не може да ви генерира нова парола, моля опитайте по-късно!</p>
		  		</c:if>
				<c:if test="${ systemProblem == null }">
					  <p>Информацията е изпратена на имейла ви.</p>
		  		</c:if>
		</div>
  <jsp:include page = "footer.jsp"/>
</body>
</html>