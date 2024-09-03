<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/2/2024
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<h2>Danh sách lô hàng</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Số theo dõi</th>
        <th>Ngày gửi</th>
        <th>Kho</th>
        <th>Điểm đến</th>
        <th>Nhà vận chuyển</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${shipments}" var="shipment">
        <tr>
            <td>${shipment.id}</td>
            <td>${shipment.trackingNumber}</td>
            <td>${shipment.shipmentDate}</td>
            <td>${shipment.warehouseLocation}</td>
            <td>${shipment.destination}</td>
            <td>${shipment.carrierName}</td>
            <td>${shipment.status}</td>
            <td>
                <a href="<c:url value='/admin/shipments/${shipment.id}' />" class="btn btn-primary">Xem</a>
                <c:choose>
                    <c:when test="${shipment.status == 'SHIPPED'}">
                        <form action="<c:url value='/admin/shipments/${shipment.id}/intransit' />" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-info">Đang giao</button>
                        </form>
                        <form action="<c:url value='/admin/shipments/${shipment.id}/cancel' />" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-danger">Hủy</button>
                        </form>
                    </c:when>
                    <c:when test="${shipment.status == 'IN_TRANSIT'}">
                        <form action="<c:url value='/admin/shipments/${shipment.id}/done' />" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-success">Đã giao</button>
                        </form>
                        <form action="<c:url value='/admin/shipments/${shipment.id}/cancel' />" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-danger">Hủy</button>
                        </form>
                    </c:when>
                    <c:when test="${shipment.status == 'DELIVERED'}">
                        <form action="<c:url value='/admin/shipments/${shipment.id}/cancel' />" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-danger">Hủy</button>
                        </form>
                    </c:when>
                    <c:when test="${shipment.status == 'RETURNED'}">
                        <!-- No additional buttons for RETURNED status -->
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>