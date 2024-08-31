package example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class MyServlet implements Servlet {
    ServletConfig myConfig;
    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
        System.out.println("I am inside the init method");
    }

    public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Enumeration<String> names = myConfig.getInitParameterNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = myConfig.getInitParameter(name);
            out.println(name + ": " + value + "<br>");
        }

        out.println("<br>Welcome to Servlets and JSP Course");
        System.out.println("I am inside the service method");
        ServletContext servletContext = myConfig.getServletContext();


        servletContext.setAttribute("MyPassword","iti");

        response.setContentType("image/apng");

    }

    public void destroy() {
        System.out.println("I am inside the destroy method");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }
}

