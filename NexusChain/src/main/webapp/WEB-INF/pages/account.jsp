<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/12/2024
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-4">
    <h1 class="text-center text-primary">Danh sách tài khoản</h1>

    <div class="row mb-4 mt-4">
        <div class="col-md-3 col-12">
            <c:url value="/admin/accounts" var="action" />
            <form action="${action}" method="get">
                <div class="mb-3">
                    <label for="fullName" class="form-label">Từ khóa:</label>
                    <input type="search" class="form-control" id="fullName" name="fullName" placeholder="Nhập từ khóa...">
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary w-100">Tìm tài khoản</button>
                </div>
            </form>
        </div>

        <div class="col-md-9 col-12">
            <form action="<c:url value='/admin/accounts' />" method="get">
                <div class="mb-3">
                    <label for="actionSelect" class="form-label">Chọn hành động:</label>
                    <select id="actionSelect" name="action" class="form-control" onchange="this.form.submit()">
                        <option value="">Chọn hành động</option>
                        <option value="confirm" ${param.action == 'confirm' ? 'selected' : ''}>Xác nhận tài khoản</option>
                        <option value="delete" ${param.action == 'delete' ? 'selected' : ''}>Xóa tài khoản</option>
                    </select>
                </div>
            </form>

            <table class="table table-hover table-bordered">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tên tài khoản</th>
                    <th>Vai trò</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="u">
                    <c:choose>
                        <c:when test="${param.action == 'confirm'}">
                            <c:if test="${!u.isDeleted}">
                                <tr>
                                    <td>${u.id}</td>
                                    <td>${u.fullName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${u.role == 'ROLE_ADMIN'}">Quản trị viên</c:when>
                                            <c:when test="${u.role == 'ROLE_DISTRIBUTOR'}">Đại lý</c:when>
                                            <c:when test="${u.role == 'ROLE_SUPPLIER'}">Nhà cung cấp</c:when>
                                            <c:otherwise>Đối tác vận chuyển</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${!u.isConfirm}">Chưa xác nhận</c:when>
                                            <c:otherwise>Đã xác nhận</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${!u.isConfirm}">
                                            <form action="<c:url value='/admin/accounts' />" method="post" class="d-inline">
                                                <input type="hidden" name="id" value="${u.id}">
                                                <button type="submit" class="btn btn-success btn-sm">Xác nhận</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                        </c:when>
                        <c:when test="${param.action == 'delete'}">
                            <c:if test="${!u.isDeleted}">
                                <tr>
                                    <td>${u.id}</td>
                                    <td>${u.fullName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${u.role == 'ROLE_ADMIN'}">Quản trị viên</c:when>
                                            <c:when test="${u.role == 'ROLE_DISTRIBUTOR'}">Đại lý</c:when>
                                            <c:when test="${u.role == 'ROLE_SUPPLIER'}">Nhà cung cấp</c:when>
                                            <c:otherwise>Đối tác vận chuyển</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${u.isDeleted}">Đã xóa</c:when>
                                            <c:otherwise>
                                                <c:if test="${!u.isConfirm}">
                                                    Chưa xác nhận
                                                </c:if>
                                                <c:if test="${u.isConfirm}">
                                                    Đã xác nhận
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${!u.isDeleted}">
                                            <form action="<c:url value='/admin/accounts/delete' />" method="post" class="d-inline">
                                                <input type="hidden" name="userId" value="${u.id}">
                                                <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${!u.isDeleted}">
                                <tr>
                                    <td>${u.id}</td>
                                    <td>${u.fullName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${u.role == 'ROLE_ADMIN'}">Quản trị viên</c:when>
                                            <c:when test="${u.role == 'ROLE_DISTRIBUTOR'}">Đại lý</c:when>
                                            <c:when test="${u.role == 'ROLE_SUPPLIER'}">Nhà cung cấp</c:when>
                                            <c:otherwise>Đối tác vận chuyển</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${!u.isDeleted}">
                                                <c:choose>
                                                    <c:when test="${!u.isConfirm}">Chưa xác nhận</c:when>
                                                    <c:otherwise>Đã xác nhận</c:otherwise>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
