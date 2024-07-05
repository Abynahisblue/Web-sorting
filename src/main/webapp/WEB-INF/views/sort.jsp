<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sort Array</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/api/sort-array" method="post">
    <label for="array">Array (comma separated):</label>
    <input type="text" id="array" name="array" pattern="\d+(,\d+)*" required />
    <br>
    <label for="algorithm">Sorting Algorithm:</label>
    <select id="algorithm" name="algorithm">
        <option value="heap">Heap Sort</option>
        <option value="quick">Quick Sort</option>
    </select>
    <br>
    <input type="submit" value="Sort" />
</form>
<br>
<c:if test="${not empty sortedArray}">
    <p>Sorted Array: ${sortedArray}</p>
</c:if>
</body>
</html>
</body>
</html>
