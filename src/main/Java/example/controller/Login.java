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

        request.getSession().setAttribute("userName", userName);
        request.getSession().setAttribute("password", password);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        includeHeader(request, response);

        UserDao userDao = new UserDao();
        Users user = userDao.getUser(userName);

        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("authenticated", userName);

            out.println("Login successful!<br>");
            
            response.sendRedirect(request.getContextPath() + "/jsp/welcome.jsp");
        }
        else{
            out.println("<span style='color:red;'>Login Unauthorized!</span><br>");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

}
