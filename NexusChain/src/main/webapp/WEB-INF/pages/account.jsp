<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/12/2024
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-primary mt-1">Danh sách tài khoản</h1>

<div class="row">
    <div class="col-md-3 col-12 bg-secondary">
        <c:url value="/admin/accounts" var="action" />
        <form action="${action}" method="get">
            <div class="mb-3 mt-3">
                <label for="fullName" class="form-label">Từ khóa:</label>
                <input type="search" class="form-control" id="fullName" placeholder="Từ khóa..." name="fullName">
            </div>
            <div class="mb-3 mt-3">
                <input type="submit" class="btn btn-success" value="Tìm tài khoản" />
            </div>
        </form>
    </div>
    <div class="col-md-9 col-12">
        <table class="table table-striped">
            <tr>
                <th>Id</th>
                <th>Tên tài khoản</th>
                <th>Vai trò</th>
                <th>Trạng thái xác nhận</th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="u">
                <tr id="product${u.id}">
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
                            <c:when test="${u.isDeleted == false}">
                                <c:choose>
                                    <c:when test="${u.isConfirm == false}">Chưa xác nhận</c:when>
                                    <c:otherwise>Đã xác nhận</c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${u.isConfirm == false}">
                            <form action="${pageContext.request.contextPath}/admin/accounts" method="post" style="display:inline;">
                                <input type="hidden" name="userId" value="${u.id}" />
                                <button type="submit" class="btn btn-success">Xác nhận</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
