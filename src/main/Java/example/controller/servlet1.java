package example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class servlet1 implements Servlet {
    ServletConfig sConf;
    @Override
    public void init(ServletConfig config) throws ServletException {
        sConf = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service (ServletRequest request,ServletResponse response)throws ServletException, IOException
    {
        ServletContext servletContext = sConf.getServletContext();
        PrintWriter out = response.getWriter();

        String str = (String) servletContext.getInitParameter("DollarExchangeRate");
        out.println("DollarExchangeRate is: " + str);

        out.println(servletContext.getAttribute("MyPassword"));
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
