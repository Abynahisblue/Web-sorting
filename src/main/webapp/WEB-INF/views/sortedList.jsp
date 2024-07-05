<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sorted Array</title>
</head>
<body>
<h1>Sorted Array</h1>
<p>Sorted Array: ${response.sortedArray}</p>

<h2>Links</h2>
<ul>
    <c:forEach var="link" items="${response.links}">
        <li><a href="${link.href}">${link.rel}</a></li>
    </c:forEach>
</ul>

<a href="<c:url value='/WEB-INF/views/sort.jsp' />">Sort Another Array</a>
</body>
</html>


