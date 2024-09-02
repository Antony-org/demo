package example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import example.db.UserDao;
import example.model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        // Set the response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        includeHeader(request, response);

        String userName = (String) request.getSession().getAttribute("authenticated");

        if (userName != null) {
            out.println("Hello: " + userName + "<br>");
            
        } else {
            response.sendRedirect(request.getContextPath());
        }

        // Display the search form
        out.println("<h1>Search Users</h1>");
        out.println("<form action=\"/myApp/search\" method=\"get\">");
        out.println("Keyword: <input type=\"text\" name=\"keyword\">");
        out.println("<input type=\"submit\" value=\"Search\">");
        out.println("</form>");

        // Use UserDao to search for users
        UserDao userDao = new UserDao();
        List<Users> users = userDao.searchUsers(keyword);

        // Display search results
        out.println("<h1>Search Results</h1>");
        if (users.isEmpty()) {
            out.println("<p>No users found.</p>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>First Name</th><th>Last Name</th><th>Username</th><th>Password</th></tr>");
            for (Users user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getUserName() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        // Include the footer
        includeFooter(request, response);
    }

    private void includeHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/header.jsp").include(request, response);
    }

    private void includeFooter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/footer.jsp").include(request, response);
    }
}