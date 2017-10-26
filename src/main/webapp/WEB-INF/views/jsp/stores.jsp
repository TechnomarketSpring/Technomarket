<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 <div style="border:1px solid red;">
      <c:forEach items="${ allNameOfStores }" var="allNameOfStores">
      <a href = "<cs:url value='/header/cities?value=${allNameOfStores}'/>">${allNameOfStores}</a>
 </c:forEach>
 </div>
<jsp:include page="footer.jsp" />
</body>
</html>