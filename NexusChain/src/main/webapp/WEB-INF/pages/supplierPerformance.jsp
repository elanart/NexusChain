<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 9/3/2024
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Supplier Performance Report</h2>

<canvas id="performanceChart" width="400" height="200"></canvas>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    const ctx = document.getElementById('performanceChart').getContext('2d');
    const chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <c:forEach var="item" items="${performanceList}">
                "${item.supplierName}",
                </c:forEach>
            ],
            datasets: [{
                label: 'Quality Rating',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                data: [
                    <c:forEach var="item" items="${performanceList}">
                    ${item.averageQualityRating},
                    </c:forEach>
                ]
            }, {
                label: 'Timely Delivery Rating',
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgba(255, 159, 64, 1)',
                data: [
                    <c:forEach var="item" items="${performanceList}">
                    ${item.averageTimelyDeliveryRating},
                    </c:forEach>
                ]
            }, {
                label: 'Cost Rating',
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                data: [
                    <c:forEach var="item" items="${performanceList}">
                    ${item.averageCostRating},
                    </c:forEach>
                ]
            }]
        }
    });
</script>
