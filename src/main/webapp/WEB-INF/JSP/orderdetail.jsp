<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="Order detail"/>
</head>
<body>
  <h1>Order ${order.id}</h1>
  <dl>
    <dt>Ordered:</dt>
    <dd>${order.orderDate}</dd>
    <dt>Required:</dt>
    <dd>${order.requiredDate}</dd>
    <dt>Customer:</dt>
    <dd>
      ${order.customer.name} <br>
      ${order.customer.streetAndNumber} <br>
      ${order.customer.postalCode} ${order.customer.state} <br>
      ${order.customer.country.name} <br>
    </dd>
    <dt>Comments:</dt>
    <dd>${order.comments}</dd>
    <dt>Details:</dt>
    <dd>
      <table>
      <thead>
        <tr>
          <th>Product</th>
          <th>Price each</th>
          <th>Quantity</th>
          <th>Value</th>
          <th>Deliverable</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="orderdetail" items="${order.orderdetails}">
        <tr>
          <td>${orderdetail.product.name}</td>
          <td>${orderdetail.priceEach}</td>
          <td>${orderdetail.quantityOrdered}</td>
          <td>${orderdetail.priceEach * orderdetail.quantityOrdered}</td>
          <td class="deliverableColumn">${orderdetail.quantityOrdered < orderdetail.product.quantityInStock ? "&check;" : "&cross;"}</td>
        </tr>
      </c:forEach>
      </tbody>
      </table>
    </dd>
    <dt>Value:</dt>
    <dd>${order.totalPrice}</dd>
  </dl>
</body>
</html>