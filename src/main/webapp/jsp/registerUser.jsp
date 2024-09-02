<%@ page import="java.sql.*" %>
<%@ page import="example.model.Users" %>
<%@ page import="example.db.UserDao" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
</head>
<body>
    <h2>Registration Successful</h2>
    <%
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (password.equals(confirmPassword)) {
            Users user = new Users(userName, password);
            UserDao userDao = new UserDao();
            userDao.addUser(user);
            
            out.println("<p>Thank you for registering, " + userName + "!</p>");
        } else {
            out.println("<p>Passwords do not match. Please try again.</p>");
            out.println("<a href='register.jsp'>Back to Registration</a>");
        }
    %>
</body>
</html>