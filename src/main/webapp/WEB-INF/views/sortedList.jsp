<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sorted Array</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2, h3 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            margin: 5px 0;
        }
        ul li a {
            color: #4CAF50;
            text-decoration: none;
        }
        ul li a:hover {
            text-decoration: underline;
        }
        .sorted-array {
            margin-top: 20px;
            padding: 10px;
            background-color: #e7f3fe;
            border: 1px solid #b3d4fc;
            border-radius: 5px;
        }
        .sorted-array span {
            margin-right: 5px;
        }
        .back-link {
            display: block;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            text-decoration: none;
        }
        .back-link:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sorted Array</h1>
    <h2>Links</h2>
    <ul>
<%--         <c:forEach var="link" items="${response.links}"> --%>
<%--             <li><a href="${link.href}">${link.rel}</a></li> --%>
<%--         </c:forEach> --%>
    </ul>

    <h3>Sorted Array</h3>
    <div class="sorted-array">
        [
        <c:forEach var="num" items="${response}">
            <span>${num},</span>
        </c:forEach>
        ]
    </div>
    <a href="<c:url value='/api/sort' />" class="back-link">Sort Another Array</a>
</div>
</body>
</html>
