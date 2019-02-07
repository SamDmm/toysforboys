<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
  <c:if test="${not empty param.mislukteOrderIds}">
    <h2>
      Shipping failed for order(s) 
      <c:forEach var="mislukteOrderId" items="${param.mislukteOrderIds}">${mislukteOrderId}, </c:forEach>
      not enough stock
    </h2>
  </c:if>
  <c:url value='/setAsShipped' var='url'></c:url>
  <form action='${url}' method='post'>
  <table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Ordered</th>
      <th>Required</th>
      <th>Customer</th>
      <th>Comments</th>
      <th>Status</th>
      <th>Ship</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="order" items="${unShippedOrders}">
    <spring:url value="/orders/{id}" var="urlOrder">
      <spring:param name="id" value="${order.id}"/>
    </spring:url>
    <tr>
      <td><a href="${urlOrder}">${order.id}</a></td>
      <td><spring:eval expression="order.orderDate"/></td>
      <td><spring:eval expression="order.requiredDate"/></td>
      <td>${order.customer.name}</td>
      <td class="textAlignLeft">${order.comments}</td>
      <td class="textAlignLeft noWrap"><img src='<c:url value="/images/${order.status}.png"/>' title='status'>${order.status}</td>
      <td class='selectColumn'><input type='checkbox' name='orderId' value='${order.id}'></td>
    </tr>
    </c:forEach>
  </tbody>
  <tfoot>
    <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td><input type='submit' value='Set As Shipped'></td>
    </tr>
  </tfoot>
  </table>
  </form>
</body>

</html>