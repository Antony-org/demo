<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <h2>Register</h2>
    <form method="POST" action="registerUser.jsp">
        <br> Username: <input type="text" name="userName" required>
        <br> Password: <input type="password" name="password" required>
        <br> Confirm Password: <input type="password" name="confirmPassword" required>
        <br> <input type="submit" value="Register">
    </form>
    <%@ include file="footer.jsp" %>
</body>
</html>