package example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import example.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sql")
public class SQLQueryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display form
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>SQL Query Tool</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>SQL Query Tool</h2>");
        out.println("<form action='sql' method='post'>");
        out.println("<label for='sql'>Enter SQL Query:</label><br>");
        out.println("<textarea id='sql' name='sql' rows='4' cols='50' placeholder='SELECT * FROM Users;'></textarea><br><br>");
        out.println("<input type='submit' value='Execute Query'>");
        out.println("</form>");
        out.println("</body>");

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("sql");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (query == null || query.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Query cannot be null or empty.");
            return;
        }

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        Query q = null;
        
        try {
            
            // Determine if the query is JPQL or Native SQL
                q = em.createNativeQuery(query, Users.class);
                if(q != null){
                List<Users> result = q.getResultList();

                out.println("<h3>Query:</h3>");
                out.println("<pre>" + query + "</pre>");
                out.println("<h3>Result:</h3>");
                for (Users u : result) {
                    out.println( u.getUserName()+ "<br>");
                }
            } else {
                out.println("<h3>Invalid Query:</h3>");
                out.println("<pre>Only SELECT queries are allowed.</pre>");
            }
        } catch (Exception e) {
            out.println("<h3>Error executing query:</h3>");
            out.println("<pre>" + query + "</pre>");
            out.println("<h3>Error message:</h3>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            em.close();
            out.close();
        }
    }
}