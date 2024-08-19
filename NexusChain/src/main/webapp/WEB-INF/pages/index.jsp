<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/12/2024
  Time: 11:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="jumbotron bg-light p-5 rounded">
    <h1 class="display-4">Chào mừng đến với NexusChain Admin</h1>
    <p class="lead">Trang quản lý dành cho quản trị viên NexusChain.</p>
    <hr class="my-4">
    <p>Dễ dàng quản lý và giám sát người dùng, hệ thống và các dịch vụ khác.</p>
<%--    <a class="btn btn-primary btn-lg" href="<c:url value='/admin/accounts' />" role="button">Danh sách người dùng</a>--%>
</div>

<div class="row mt-5">
    <div class="col-md-4">
        <div class="card h-100 text-center">
            <div class="card-body">
                <h5 class="card-title">Quản lý người dùng</h5>
                <p class="card-text">Duyệt, xác nhận, hoặc vô hiệu hóa người dùng trên hệ thống.</p>
                <a href="<c:url value='/admin/accounts' />" class="btn btn-outline-primary">Xem chi tiết</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card h-100 text-center">
            <div class="card-body">
                <h5 class="card-title">Thống kê</h5>
                <p class="card-text">Xem báo cáo và thống kê về hoạt động hệ thống.</p>
                <a href="#" class="btn btn-outline-primary">Xem chi tiết</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card h-100 text-center">
            <div class="card-body">
                <h5 class="card-title">Cấu hình hệ thống</h5>
                <p class="card-text">Thiết lập và quản lý cấu hình hệ thống một cách dễ dàng.</p>
                <a href="#" class="btn btn-outline-primary">Xem chi tiết</a>
            </div>
        </div>
    </div>
</div>
