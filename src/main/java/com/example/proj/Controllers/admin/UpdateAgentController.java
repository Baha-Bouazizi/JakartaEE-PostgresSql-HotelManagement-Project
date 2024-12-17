package com.example.proj.Controllers.admin;

import com.example.proj.Models.Account;
import com.example.proj.dao.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateAgent")
public class UpdateAgentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        AccountDao accountDAO = new AccountDao();
        try {
            // Fetch the agent details
            Account agent = accountDAO.getAgentByUsername(username);
            request.setAttribute("agent", agent);
            request.getRequestDispatcher("/updateAgent.jsp").forward(request, response); // Forward to the update form
        } catch (SQLException e) {
            response.getWriter().write("Error fetching agent details: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        AccountDao accountDAO = new AccountDao();
        try {
            // Update the agent's details
            accountDAO.updateAgent(username, email);
            response.sendRedirect("/Proj_war_exploded/listAgent"); // Redirect back to the list
        } catch (SQLException e) {
            response.getWriter().write("Error updating agent: " + e.getMessage());
        }
    }
}
