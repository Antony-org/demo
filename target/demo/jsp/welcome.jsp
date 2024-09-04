<%@ page import="example.model.Users" %>
<%@ page import="example.db.UserDao" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h2>User Information</h2>

<%
    UserDao userDao = new UserDao();
    userDao.addUser(new Users("user1", "pass1"));
    userDao.addUser(new Users("user2", "pass2"));
    userDao.addUser(new Users("user3", "pass3"));

    List<Users> userList = userDao.getAllUsers("username");

    session.setAttribute("userList", userList);
%>

<p>Hello, <c:out value="${authenticated}"/>! Here is the list of users:</p>

<a href="search.jsp">Search</a>

<jsp:include page="footer.jsp" />
</body>
</html>