<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Users</title>
</head>
<body>
    <div class="container">
        <h1>Search Users</h1>
        <form action="/myApp/search" method="get">
            <label for="keyword">Keyword:</label>
            <input type="text" id="keyword" name="keyword">
            <input type="submit" value="Search">
        </form>
        <div id="search-results"></div>
    </div>
</body>
</html>