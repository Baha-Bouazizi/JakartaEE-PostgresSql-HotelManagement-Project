package com.example.proj.Controllers.admin;

import com.example.proj.Models.Account;
import com.example.proj.dao.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listAgent")
public class ListAgentsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDao accountDAO = new AccountDao();
        try {
            // Fetch the list of agents
            List<Account> agents = accountDAO.getAgents();
            // Set the agents as a request attribute
            request.setAttribute("agents", agents);
            // Forward the request to the JSP
            request.getRequestDispatcher("/listAgent.jsp").forward(request, response);
        } catch (SQLException e) {
            response.getWriter().write("Error fetching agents: " + e.getMessage());
        }
    }
}
