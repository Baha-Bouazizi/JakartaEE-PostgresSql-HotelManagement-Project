package com.example.proj.Controllers.auth;

import com.example.proj.Models.Account;
import com.example.proj.dao.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    // For displaying the registration form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the JSP for registration form
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    // For handling the form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Create the account object with the role hardcoded as "client"
        Account account = new Account(username, password, email, "client");
        AccountDao accountDAO = new AccountDao();

        try {
            // Create the account in the database
            accountDAO.createAccount(account);
            response.sendRedirect("/Proj_war_exploded/login"); // Redirect to login page after successful registration
        } catch (SQLException | NoSuchAlgorithmException e) {
            response.getWriter().write("Error during registration: " + e.getMessage());
        }
    }
}
