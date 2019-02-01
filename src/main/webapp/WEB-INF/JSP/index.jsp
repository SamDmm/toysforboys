<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="Unshipped orders"/>
</head>
<body>
  <h1>Unshipped orders</h1>
  <c:if test="${empty unShippedOrders}">
    <p>GEEN ORDERS GEVONDEN</p>
  </c:if>
  <c:forEach var='order' items='${unShippedOrders}'>
    <p>${order.id}</p>
  </c:forEach>
</body>
</html>