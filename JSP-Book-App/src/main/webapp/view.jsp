<!DOCTYPE html>
<html>

<head>
    <title>View Books</title>
</head>

<body>
    <h2>List of Books</h2>
    <table border="1">
        <tr>
            <th>Book Name</th>
            <th>Author Name</th>
            <th>Price</th>
        </tr>
        <c:forEach var="books" items="${books}">
            <tr>
                <td>${book.book_name}</td>
                <td>${book.author_name}</td>
                <td>${book.price}</td>
            </tr>
        </c:forEach>
    </table>
</body>

</html>