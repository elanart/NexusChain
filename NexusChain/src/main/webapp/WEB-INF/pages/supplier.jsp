<%-- 
    Document   : supplier
    Created on : Aug 1, 2024, 1:50:41 PM
    Author     : tuann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center mt-3">QUẢN LÝ NHÀ CUNG CẤP</h1>
<div class="row">
    <div class="col-md-12 col-12">
        <table class="table table-striped">
            <tr>
                <th>Tên</th>
                <th>Địa chỉ</th>
                <th>SÐT</th>
                <th>Liên hệ</th>
                <th>Điều kiện thanh toán</th>
                <th>Đánh giá</th>
                <th>Tùy chỉnh</th>
            </tr>
            <c:forEach items="${suppliers}" var="s">
                <tr>
                    <td>${s.name}</td>
                    <td>${s.address}</td>
                    <td>${s.phone}</td>
                    <td>${s.contactInfo}</td>
                    <td>${s.paymentTerms}</td>
                    <td>${String.format(s.rating)}</td>
                    <td>
                        <a href="/supplier" class="btn btn-info">Sửa</a>
                        <button class="btn btn-danger">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-12">
        <c:url value="/supplier" var="action"/>
        <form:form method="post" action="${action}" modelAttribute="supplier" enctype="multipart/form-data">
            <div class="mb-3 mt-3">
                <label for="name" class="form-label">Tên</label>
                <form:input path="name" type="text" class="form-control" id="name" placeholder="Tên nha cung cap" name="name" />
                <form:errors path="name" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ</label>
                <form:input path="address" type="text" class="form-control" id="address" placeholder="Dia chi nha cung cap" name="address" />
                <form:errors path="address" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <form:input path="phone" type="text" class="form-control" id="phone" placeholder="So dien thoai nha cung cap" name="phone" />
                 <form:errors path="phone" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="contactInfo" class="form-label">Thông tin liên lạc</label>
                <form:input path="contactInfo" type="text" class="form-control" id="contactInfo" placeholder="Thong tin lien lac nha cung cap" name="contactInfo" />
                 <form:errors path="contactInfo" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="paymentTerms" class="form-label">Điều kiện thanh toán</label>
                <form:input path="contactInfo" type="text" class="form-control" id="paymentTerms" placeholder="Dieu kien thanh toan cua nha cung cap" name="paymentTerms" />
                 <form:errors path="contactInfo" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="rating" class="form-label">Điểm dánh giá</label>
                <form:input path="rating" type="number" class="form-control" id="rating" placeholder="Diem danh gia cua nha cung cap" name="rating" />
            </div>
            <div class="mb-3 mt-3">
                <button type="submit" class="btn btn-success">Thêm nhà cung cấp</button>
            </div>
        </form:form>
    </div>
</div>
