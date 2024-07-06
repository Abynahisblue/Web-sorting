<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sort Array</title>
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
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h1 {
            margin-top: 0;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group input[type="submit"],
        .form-group input[type="button"] {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }
        .form-group input[type="submit"]:hover,
        .form-group input[type="button"]:hover {
            background-color: #45a049;
        }
        .sorted-array {
            margin-top: 20px;
            padding: 10px;
            background-color: #e7f3fe;
            border: 1px solid #b3d4fc;
            border-radius: 5px;
        }
        .json-response {
            margin-top: 20px;
            padding: 10px;
            background-color: #e7f3fe;
            border: 1px solid #b3d4fc;
            border-radius: 5px;
            word-wrap: break-word;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#jsonButton").click(function(event) {
                event.preventDefault();
                const array = $("#array").val();
                const algorithm = $("#algorithm").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/api/sort/array",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({
                        array: array,
                        algorithm: algorithm
                    }),
                    success: function(response) {
                        $(".json-response").html(JSON.stringify(response));
                    },
                    error: function(xhr, status, error) {
                        $(".json-response").html("An error occurred: " + xhr.responseText);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="form-container">
    <h1>Sort Array</h1>
    <form action="${pageContext.request.contextPath}/api/sort-array" method="post">
        <div class="form-group">
            <label for="array">Array (comma separated):</label>
            <input type="text" id="array" name="array" pattern="\d+(,\d+)*" required />
        </div>
        <div class="form-group">
            <label for="algorithm">Sorting Algorithm:</label>
            <select id="algorithm" name="algorithm">
                <option value="heap">Heap Sort</option>
                <option value="quick">Quick Sort</option>
                <option value="radix">Radix Sort</option>
                <option value="bucket">Bucket Sort</option>
                <option value="merge">Merge Sort</option>
            </select>
        </div>
        <div class="form-group">
            <input type="submit" value="Sort" />
        </div>
        <div class="form-group">
            <input type="button" id="jsonButton" value="Get JSON Response" />
        </div>
    </form>
    <c:if test="${not empty sortedArray}">
        <div class="sorted-array">
            <p>Sorted Array: ${sortedArray}</p>
        </div>
    </c:if>
    <div class="json-response"></div>
</div>
</body>
</html>
