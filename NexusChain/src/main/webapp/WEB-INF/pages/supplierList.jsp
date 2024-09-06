<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/3/2024
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">Danh sách nhà cung cấp</h1>

<div class="row">
    <div class="col-md-8 col-12">
        <table class="table table-striped">
            <tr>
                <th>Tên nhà cung cấp</th>
                <th>Hành động</th>
            </tr>
            <c:forEach items="${suppliers}" var="supplier">
                <tr>
                    <td>${supplier.fullName}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/suppliers/${supplier.id}/ratings" class="btn btn-primary">Đánh giá</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
