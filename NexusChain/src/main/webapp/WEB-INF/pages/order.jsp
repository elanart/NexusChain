<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/22/2024
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2>Danh sách đơn hàng</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Loại đơn hàng</th>
        <th>Trạng thái</th>
        <th>Người đặt hàng</th>
        <th>Ngày đặt hàng</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.type.displayName}</td>
            <td>${order.status}</td>
            <td>${order.userFullName}</td>
            <td>${order.orderDate}</td>
            <td>
                <a href="<c:url value='/admin/orders/${order.id}' />" class="btn btn-primary">Xem</a>
                <c:if test="${order.status == 'PENDING'}">
                    <form action="<c:url value='/admin/orders/${order.id}/confirm' />" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-success">Xác nhận</button>
                    </form>
                    <form action="<c:url value='/admin/orders/${order.id}/cancel' />" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-danger">Hủy</button>
                    </form>
                </c:if>

                <c:if test="${order.status == 'COMPLETED'}">
                    <form action="<c:url value='/admin/orders/${order.id}/cancel' />" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-danger">Hủy</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
