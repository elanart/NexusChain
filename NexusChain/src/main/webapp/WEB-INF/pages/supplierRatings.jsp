<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/3/2024
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">Đánh giá nhà cung cấp</h1>

<div class="row">
    <div class="col-md-8 col-12">
        <h3 class="text-center text-primary">Đánh giá mới</h3>
        <form action="${pageContext.request.contextPath}/admin/suppliers/${supplierId}/ratings" method="post">
            <div class="form-group">
                <label for="criterion">Tiêu chí</label>
                <select id="criterion" name="criterion" class="form-control">
                    <c:forEach items="${criteria}" var="criterion">
                        <option value="${criterion.name()}">${criterion.displayName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="rating">Đánh giá</label>
                <input type="number" step="0.01" id="rating" name="rating" class="form-control" min="0" max="10">
            </div>
            <div class="form-group">
                <label for="comment">Bình luận</label>
                <textarea id="comment" name="comment" class="form-control"></textarea>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Lưu</button>
        </form>

    </div>
</div>
