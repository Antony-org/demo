package example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Cookie testCookie = new Cookie("test", "testValue");

        testCookie.setMaxAge(60 * 60 * 24);

        response.addCookie(testCookie);

        out.println("<html>");
        out.println("<head><title>Test Cookie</title></head>");
        out.println("<body>");
        out.println("<h2>Cookie has been set successfully.</h2>");
        out.println("<p><a href='" + request.getContextPath() + "/retrieve-cookie'>Click here to retrieve the cookie</a></p>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}