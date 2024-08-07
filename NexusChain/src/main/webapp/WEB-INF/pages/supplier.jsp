<%-- 
    Document   : supplier
    Created on : Aug 1, 2024, 1:50:41 PM
    Author     : tuann
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center mt-3">QUẢN LÝ NHÀ CUNG CẤP</h1>
<div class="row">
    <div class="col-md-6 col-12">
        <c:url value="supplier" var="action" />
        <form:form method="post" action="${action}" modelAttribute="supplier" enctype="multipart/form-data">
            <div class="mb-3 mt-3">
                <label for="fullName" class="form-label">Tên</label>
                <form:input path="user.fullName" type="text" class="form-control" id="fullName" placeholder="Tên nhà cung cấp" name="fullName" />
                <form:errors path="user.fullName" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <form:input path="user.phone" type="text" class="form-control" id="phone" placeholder="Số điện thoại nhà cung cấp" name="phone" />
                <form:errors path="user.phone" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">Email</label>
                <form:input path="user.email" type="text" class="form-control" id="email" placeholder="Email nhà cung cấp" name="email" />
                <form:errors path="user.email" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ</label>
                <form:input path="user.address" type="text" class="form-control" id="address" placeholder="Địa chỉ nhà cung cấp" name="address" />
                <form:errors path="user.address" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="paymentTerms" class="form-label">Điều khoản thanh toán</label>
                <form:input path="paymentTerms" type="text" class="form-control" id="paymentTerms" placeholder="Điều khoản thanh toán" name="paymentTerms" />
                <form:errors path="paymentTerms" element="div" cssClass="alert alert-danger" />
            </div>
            <div class="mb-3 mt-3">
                <label for="file" class="form-label">Ảnh đại diện</label>
                <form:input path="file" accept=".png, .jpg, .webp" type="file" class="form-control" id="file" name="file" />
            </div>
            <div class="mb-3 mt-3">
                <button type="submit" class="btn btn-primary">Lưu</button>
            </div>
        </form:form>
    </div>
</div>
