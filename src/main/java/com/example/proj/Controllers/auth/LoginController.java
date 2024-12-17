package com.example.proj.Controllers.auth;

import com.example.proj.dao.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/login")  // Use @WebServlet to map this servlet to /login
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountDao accountDAO = new AccountDao();
        try {
            // Validate the user credentials
            String role = accountDAO.validateLoginAndGetRole(username, password);
            if (role != null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);  // Store username in session
                session.setAttribute("role", role);          // Store role in session

                // Redirect based on role
                if ("admin".equals(role)) {
                    response.sendRedirect("/Proj_war_exploded/adminDashboard");  // Redirect to admin dashboard
                } else if ("agent".equals(role)) {
                    response.sendRedirect("/Proj_war_exploded/agentDashboard");  // Redirect to agent dashboard (if applicable)
                } else {
                    response.sendRedirect("/Proj_war_exploded/userDashboard");   // Redirect to user dashboard
                }
            } else {
                response.getWriter().write("Invalid login credentials.");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            response.getWriter().write("Error during login: " + e.getMessage());
        }
    }
}
