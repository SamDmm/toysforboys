<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false"%>
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
  <table>
    <tr>
      <th>ID</th>
      <th>Ordered</th>
      <th>Required</th>
      <th>Customer</th>
      <th>Comments</th>
      <th>Status</th>
      <th>Ship</th>
    </tr>
    <c:forEach var="order" items="${unShippedOrders}">
    <tr>
      <td>${order.id}</td>
      <td>${order.orderDate}</td>
      <td>${order.requiredDate}</td>
      <td>${order.customerId}</td>
      <td>${order.comments}</td>
      <td><img src='<c:url value="/images/${order.status}.png"/>' title='status'>${order.status}</td>
      <td></td>
    </tr>
    </c:forEach>
  </table>
</body>

</html>