  <%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/12/2024
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
      <div class="container-fluid">
          <a class="navbar-brand" href="#">NexusChain Admin</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="collapsibleNavbar">
              <ul class="navbar-nav">
                  <li class="nav-item">
                      <a class="nav-link" href="<c:url value='/admin' />">Trang chủ</a>
                  </li>
                  <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-bs-toggle="dropdown">
                          Quản lý
                      </a>
                      <div class="dropdown-menu">
                          <a class="dropdown-item" href="<c:url value='/admin/accounts' />">Người dùng</a>
                          <a class="dropdown-item" href="<c:url value='/admin/orders' />">Đơn hàng</a>
                          <a class="dropdown-item" href="<c:url value='/admin/shipments' />">Lô hàng</a>
                          <a class="dropdown-item" href="<c:url value='/admin/suppliers' />">Nhà cung cấp</a>
                          <a class="dropdown-item" href="<c:url value='/admin/warehouse' />">Kho</a>
                      </div>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="<c:url value='/admin/inventory-report' />">Thống kê hàng tồn kho</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="<c:url value='/admin/suppliers/performance' />">Thống kê hiệu suất nhà cung cấp</a>
                  </li>
                  <s:authorize access="!isAuthenticated()">
                      <li class="nav-item">
                          <a class="nav-link" href="<c:url value='/login' />">Đăng nhập</a>
                      </li>
                  </s:authorize>
                  <s:authorize access="isAuthenticated()">
                      <li class="nav-item">
                        <span class="nav-link">
                            Welcome <s:authentication property="principal.username" />!
                        </span>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" href="<c:url value='/logout' />">Đăng xuất</a>
                      </li>
                  </s:authorize>
              </ul>
          </div>
      </div>
  </nav>