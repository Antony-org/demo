<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
</head>
<body>
    <jsp:include page="jsp/header.jsp" />

    <form method="POST" action="Login">
        <br> Username: <input type="text" name="userName">
        <br> Password: <input type="password" name="password">
        <br> <input type="submit" value="Login">
    </form>

    <p>Don't have an account? <a href="jsp/register.jsp">Register here</a></p>
    <%@ include file="jsp/footer.jsp" %>

</body>
<hr>
<footer>
    <p>&copy; 2024 My Application. All rights reserved.</p>
</footer>
</html>