package com.example.proj.Controllers.shared;

import com.example.proj.Models.Hotel;
import com.example.proj.Models.HotelRoomType;
import com.example.proj.Models.RoomType;
import com.example.proj.dao.HotelDao;
import com.example.proj.dao.HotelRoomTypeDao;
import com.example.proj.dao.RoomTypeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showHotelDetails")
public class HotelDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int hotelId = Integer.parseInt(request.getParameter("id")); // Get the hotel ID from the request

        HotelDao hotelDao = new HotelDao();
        RoomTypeDao roomTypeDao = new RoomTypeDao();
        HotelRoomTypeDao hotelRoomTypeDao = new HotelRoomTypeDao();

        try {
            // Fetch hotel details by ID
            Hotel hotel = hotelDao.getHotelById(hotelId);

            // Fetch room types associated with the hotel
            List<RoomType> roomTypes = roomTypeDao.getRoomTypes();

            // Fetch the number of each room type available in the hotel
            List<HotelRoomType> hotelRoomTypes = hotelRoomTypeDao.getHotelRoomTypesForHotel(hotelId);

            // Set attributes for the view
            request.setAttribute("hotel", hotel);
            request.setAttribute("roomTypes", roomTypes);
            request.setAttribute("hotelRoomTypes", hotelRoomTypes);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error fetching hotel details: " + e.getMessage());
            return;
        }

        // Forward to the view page
        request.getRequestDispatcher("/showHotelDetails.jsp").forward(request, response);
    }
}
