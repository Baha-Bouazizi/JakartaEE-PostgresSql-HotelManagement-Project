package com.example.proj.Controllers.admin;

import com.example.proj.dao.AccountDao;
import com.example.proj.Models.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);  // Get the session if it exists
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            // If the user is not logged in or not an admin, redirect to login
            response.sendRedirect("/Proj_war_exploded/login");
            return;
        }

        // If the user is an admin, proceed to the admin dashboard
        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }
}

