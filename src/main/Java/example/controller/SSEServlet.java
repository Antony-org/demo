package example.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class SSEServlet extends HttpServlet {
    @Serial
    List<String> messages = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("wth");
        resp.setContentType("text/event-stream");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String lastId = req.getHeader("Last-Event-ID");
        System.out.println("inside get");
        int msgId = (lastId != null) ? Integer.parseInt(lastId) : 0;
        for (int i = msgId; i < messages.size(); i++) {
            out.write("id: " + (i + 1) + "\n");
            out.write("data: " + messages.get(i) + "<br>" + "\n\n");
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("msg");
        String username = req.getParameter("username");
        if (message != null && !message.trim().equals("")) messages.add(username + ": " + message);
    }

}
