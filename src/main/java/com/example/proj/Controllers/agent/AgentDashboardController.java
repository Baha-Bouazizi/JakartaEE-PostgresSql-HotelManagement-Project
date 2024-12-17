package com.example.proj.Controllers.agent;

import com.example.proj.Models.Hotel;
import com.example.proj.dao.HotelDao;
import com.example.proj.dao.RoomTypeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agentDashboard")
public class AgentDashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session if exists
        if (session == null || !"agent".equals(session.getAttribute("role"))) {
            response.sendRedirect("/Proj_war_exploded/login");
            return;
        }

        String username = (String) session.getAttribute("username"); // Get agent's username
        HotelDao hotelDao = new HotelDao();
        RoomTypeDao roomTypeDao = new RoomTypeDao();

        // Get filter parameters from the request
        String city = request.getParameter("city");
        String stars = request.getParameter("stars");
        String priceMin = request.getParameter("priceMin");
        String priceMax = request.getParameter("priceMax");

        try {
            // Fetch statistics
            int hotelCount = hotelDao.getHotelCount();
            int roomTypeCount = roomTypeDao.getRoomTypeCount();

            // Fetch filtered hotels
            List<Hotel> hotels = hotelDao.getHotelsFiltered(city, stars, priceMin, priceMax);

            if (hotels == null) {
                hotels = new ArrayList<>(); // Ensure the list is never null
            }

            request.setAttribute("username", username);
            request.setAttribute("hotelCount", hotelCount);
            request.setAttribute("roomTypeCount", roomTypeCount);
            request.setAttribute("hotels", hotels);

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching dashboard data: " + e.getMessage());
        }

        request.getRequestDispatcher("/agentDashboard.jsp").forward(request, response);
    }
}
