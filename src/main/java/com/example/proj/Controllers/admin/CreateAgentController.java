package com.example.proj.Controllers.admin;

import com.example.proj.Models.Account;
import com.example.proj.dao.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/createAgent")
public class CreateAgentController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the form to create a new agent
        request.getRequestDispatcher("/createAgent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Account account = new Account(username, password, email, "agent");
        AccountDao accountDAO = new AccountDao();

        try {
            accountDAO.createAccount(account); // Save the new agent to DB
            response.sendRedirect("/Proj_war_exploded/adminDashboard"); // Redirect to dashboard
        } catch (SQLException | NoSuchAlgorithmException e) {
            response.getWriter().write("Error during agent creation: " + e.getMessage());
        }
    }
}
