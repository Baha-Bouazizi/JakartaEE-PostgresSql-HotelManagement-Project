package com.example.proj.Controllers.user;

import com.example.proj.Models.ReservationEnhanced;
import com.example.proj.dao.ReservationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/history")
public class HistoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdString = request.getParameter("userId");

        if (userIdString == null || userIdString.isEmpty()) {
            // Redirect to login if userId is missing
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            Integer userId = Integer.parseInt(userIdString);

            // Fetch reservations for this user
            ReservationDao reservationDao = new ReservationDao();
            List<ReservationEnhanced> reservations = reservationDao.getReservationsByUserId(userId);
            request.setAttribute("reservations", reservations);

            // Forward to the history.jsp page
            request.getRequestDispatcher("/history.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid userId in request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
        }
    }
}
