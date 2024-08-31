package example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<header>");
        out.println("<h1>My Application Header</h1>");
        out.println("<nav>");
        out.println("<a href='index.html'>Home</a> | ");
        out.println("<a href='Login'>Login</a>");
        out.println("</nav>");
        out.println("</header>");
    }
}