<%-- 
    Document   : header
    Created on : Jul 31, 2024, 11:53:07 PM
    Author     : tuann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #0866ff">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>">NEXUS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/>" style=" color:#fff">Trang chủ</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/supplier"/>" style=" color:#fff">Quản lý nhà cung cấp</a>
                </li>
            </ul>
        </div>
    </div>
</nav>