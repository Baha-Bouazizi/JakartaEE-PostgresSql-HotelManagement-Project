package com.example.proj.Controllers.admin;

import com.example.proj.dao.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteAgent")
public class DeleteAgentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        AccountDao accountDAO = new AccountDao();
        try {
            // Delete the agent
            accountDAO.deleteAgent(username);
            response.sendRedirect("/Proj_war_exploded/listAgent"); // Redirect back to the list
        } catch (SQLException e) {
            response.getWriter().write("Error deleting agent: " + e.getMessage());
        }
    }
}
