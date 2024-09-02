<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/2/2024
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<h2>Chi tiết lô hàng</h2>
<p>ID Lô hàng: ${shipment.id}</p>
<p>Số theo dõi: ${shipment.trackingNumber}</p>
<p>Ngày gửi: ${shipment.shipmentDate}</p>
<p>Ngày giao dự kiến: ${shipment.expectedDelivery}</p>
<p>Kho: ${shipment.warehouseLocation}</p>
<p>Điểm đến: ${shipment.destination}</p>
<p>Nhà vận chuyển: ${shipment.carrierName}</p>
<p>Trạng thái: ${shipment.status}</p>

<a href="<c:url value='/admin/shipments' />" class="btn btn-secondary">Trở về danh sách</a>
