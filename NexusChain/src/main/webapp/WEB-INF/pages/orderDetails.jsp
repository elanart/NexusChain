<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/1/2024
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Chi tiết đơn hàng</h2>
<p>ID Đơn hàng: ${order.id}</p>
<p>Loại đơn hàng: ${order.type.displayName}</p>
<p>Người đặt hàng: ${order.userFullName}</p>
<p>Trạng thái: ${order.status}</p>
<p>Ngày đặt hàng: ${order.orderDate}</p>

<h3>Chi tiết sản phẩm</h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Giá</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${order.orderDetails}" var="detail">
        <tr>
            <td>${detail.productName}</td>
            <td>${detail.quantity}</td>
            <td>${detail.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<c:url value='/admin/orders' />" class="btn btn-secondary">Trở về danh sách</a>