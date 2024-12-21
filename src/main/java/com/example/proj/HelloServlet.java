package com.example.proj;

import com.example.proj.Models.Hotel;
import com.example.proj.dao.HotelDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/home")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelDao hotelDao = new HotelDao();
        try {
            // Fetch all hotels from the database
            List<Hotel> hotels = hotelDao.getHotels();
            request.setAttribute("hotels", hotels);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching hotels: " + e.getMessage());
        }

        // Forward the request to the home JSP
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
