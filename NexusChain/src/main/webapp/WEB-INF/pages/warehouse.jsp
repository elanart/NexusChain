<%-- 
    Document   : warehouse
    Created on : Sep 2, 2024, 2:30:42 PM
    Author     : tuann
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center mt-3">QUAN LY KHO HANG</h1>
<table class="table table-striped mt-3">
    <tr>
        <th>ID</th>
        <th>Địa chỉ</th>
        <th>Dung tích</th>
        <th>Giá thuê</th>
        <th>Ngày tạo</th>
        <th>Ngày chỉnh sửa</th>
        <th>Trạng thái</th>
    </tr>
    <c:forEach items="${warehouse}" var="w">
        <tr>
            <td>${w.id}</td>
            <td>${w.location}</td>
            <td>${w.capacity}</td>
            <td>${w.cost}</td>
            <td><fmt:formatDate value="${w.createdDate}" pattern="HH:mm dd-MM-yyyy" /></td>
            <td><fmt:formatDate value="${w.updatedDate}" pattern="HH:mm dd-MM-yyyy" /></td>
    <td>${w.isActive}</td>
</tr>
</c:forEach>
</table>