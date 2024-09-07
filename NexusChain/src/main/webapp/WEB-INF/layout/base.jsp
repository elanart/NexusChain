<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 8/12/2024
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <%--    <script src="<c:url value="/js/script.js" />"></script>--%>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            /* Ensure the body takes the full height of the viewport */
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                margin: 0;
            }

            .container {
                flex: 1;
                padding-bottom: 60px; /* Add padding equal to the footer height */
            }

            .footer {
                position: fixed;
                bottom: 0;
                left: 0;
                width: 100%;
                background-color: #007bff; /* Replace with your desired background color */
                color: white;
                text-align: center;
                padding: 10px 0;
                z-index: 1000; /* Ensures the footer stays on top of other elements */
                height: 60px; /* Set a fixed height for the footer */
            }

            /* Footer content */
            .footer-content {
                margin: 0 auto;
                max-width: 1200px; /* Adjust based on your layout needs */
            }
        </style>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div class="container">
            <tiles:insertAttribute name="content" />
        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>
