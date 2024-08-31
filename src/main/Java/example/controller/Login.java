package example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import example.db.UserDao;
import example.model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        includeHeader(request, response);

        UserDao userDao = new UserDao();
        Users user = userDao.getUser(userName);

        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("authenticated", userName);

            out.println("Login successful!<br>");
            
            response.sendRedirect(request.getContextPath() + "/search");
        }
        else{
            out.println("<span style='color:red;'>Login Unauthorized!</span><br>");
            request.getRequestDispatcher("index.html").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        includeHeader(request, response);

        String userName = (String) request.getSession().getAttribute("userName");
        String password = (String) request.getSession().getAttribute("password");
        
        out.println("User ID: " + userName + "<br>");
        out.println("Password: " + password + "<br>");
        
        includeFooter(request, response);
    }
}
