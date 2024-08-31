package example.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    protected void includeHeader(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher headerDispatcher = request.getRequestDispatcher("/WEB-INF/header.html");
        headerDispatcher.include(request, response);
    }

    protected void includeFooter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher footerDispatcher = request.getRequestDispatcher("/WEB-INF/footer.html");
        footerDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                includeHeader(request,response);
                tonyPost(request, response);
                includeFooter(request, response);
            }

    protected void tonyPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}