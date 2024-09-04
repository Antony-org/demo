<%@ page import="java.sql.*" %>
<%@ page import="example.db.UserDao" %>
<%@ page import="example.model.Users" %>
<%
    response.setContentType("text/html;charset=UTF-8");
    String name = request.getParameter("name");

        UserDao userDao = new UserDao();
        Users user = userDao.getUser(name);

        if (user != null) {
            response.getWriter().write("valid");
        } else {
            response.getWriter().write("nah");
        }


%>