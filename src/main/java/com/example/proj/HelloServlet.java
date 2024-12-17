package com.example.proj;

import com.example.proj.utils.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        // Test Database Connection
        try (Connection conn = DatabaseConnection.getInstance()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT 'Connection Successful' AS status";
                var rs = stmt.executeQuery(query);

                while (rs.next()) {
                    out.println("<p>" + rs.getString("status") + "</p>");
                }
            } else {
                out.println("<p>Connection is null.</p>");
            }
        } catch (SQLException e) {
            out.println("<p>Database connection failed: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
