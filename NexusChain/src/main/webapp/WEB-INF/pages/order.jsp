<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/22/2024
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-4">
    <h2>Danh sách đơn hàng</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Khách hàng</th>
            <th>Ngày đặt hàng</th>
            <th>Trạng thái</th>
            <th>Tổng tiền</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.customerName}</td>
                <td>${order.orderDate}</td>
                <td>${order.status}</td>
                <td>${order.totalAmount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
