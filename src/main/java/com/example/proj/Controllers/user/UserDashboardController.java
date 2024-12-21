package com.example.proj.Controllers.user;

import com.example.proj.dao.HotelDao;
import com.example.proj.dao.ReservationDao;
import com.example.proj.Models.Hotel;
import com.example.proj.Models.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userDashboard") // Map this servlet to /userDashboard
public class UserDashboardController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username from the session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            // Fetch the reservations for the logged-in user
            ReservationDao reservationDAO = new ReservationDao();

            // Fetch the list of available hotels
            HotelDao hotelDAO = new HotelDao();
            List<Hotel> hotels = null;
            try {
                hotels = hotelDAO.getHotels();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Set the reservations and hotels as attributes to be accessed in the view
            request.setAttribute("hotels", hotels);

            // Forward to the userDashboard.jsp view
            request.getRequestDispatcher("/userDashboard.jsp").forward(request, response);
        } else {
            // If no user is logged in, redirect to the login page
            response.sendRedirect("/Proj_war_exploded/login");
        }
    }
}
