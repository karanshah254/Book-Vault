<!DOCTYPE html>
<html>

<head>
    <title>Book Form</title>
</head>

<body>
    <h2>Add a New Book</h2>
    <form action="book" method="POST">
        <input type="hidden" name="action" value="save">
        <label for="book_name">Book Name:</label>
        <input type="text" id="book_name" name="book_name"><br><br>
        <label for="author_name">Author Name:</label>
        <input type="text" id="author_name" name="author_name"><br><br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price"><br><br>
        <button type="submit">Save Book</button>
    </form>

    <br>

    <form action="book" method="POST">
        <input type="hidden" name="action" value="view">
        <button type="submit">View All Books</button>
    </form>
</body>

</html>