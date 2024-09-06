<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/3/2024
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">THỐNG KÊ TÌNH TRẠNG TỒN KHO</h1>

<div class="row">
    <div class="col-md-5 col-12">
        <table class="table table-striped">
            <tr>
                <th>Loại hàng hóa</th>
                <th>Số lượng</th>
            </tr>
            <tr>
                <td>Hàng hóa hiện tại</td>
                <td>${inventory.totalItems}</td>
            </tr>
            <tr>
                <td>Hàng hóa sắp hết hạn</td>
                <td>${inventory.nearExpiryItems}</td>
            </tr>
            <tr>
                <td>Hàng hóa đã hết hạn</td>
                <td>${inventory.expiredItems}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-7 col-12">
        <canvas id="inventoryChart"></canvas>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    window.onload = function () {
        const ctx = document.getElementById('inventoryChart');

        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Hàng hóa hiện tại', 'Hàng hóa sắp hết hạn', 'Hàng hóa đã hết hạn'],
                datasets: [{
                    label: 'Số lượng',
                    data: [${inventory.totalItems}, ${inventory.nearExpiryItems}, ${inventory.expiredItems}],
                    backgroundColor: ['#36a2eb', '#ffce56', '#ff6384'],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
</script>