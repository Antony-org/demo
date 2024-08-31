package example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RetrieveCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        boolean cookieFound = false;

        if (cookies != null) {
            out.println("<h2>searching </h2>");
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("test")) {

                    out.println("<h2>Cookie Found: " + cookie.getName() + " = " + cookie.getValue() + "</h2>");
                    cookieFound = true;
                    break;
                }
            }
        }

        if (!cookieFound) {
            out.println("<h2>Cookie not found!</h2>");
            out.println("<p>Please enable cookies in your browser to continue.</p>");
        }

        out.close();
    }
}