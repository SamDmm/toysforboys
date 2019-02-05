<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Test Pagina</title>
</head>
<body>
  <h1>Test Pagina</h1>
  <c:forEach var="mislukteOrderId" items="${mislukteOrderIds }">
    <p>${mislukteOrderId}</p>
  </c:forEach>
</body>
</html>