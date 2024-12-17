package com.example.proj.Controllers.agent;

import com.example.proj.Models.HotelRoomType;
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

@WebServlet("/manageHotelRoomTypes")
public class HotelRoomTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelRoomTypeDao dao = new HotelRoomTypeDao();
        HotelDao hotelDao = new HotelDao();
        RoomTypeDao roomTypeDao = new RoomTypeDao();

        try {
            // Fetch hotel-room-type links
            List<HotelRoomType> hotelRoomTypes = dao.getHotelRoomTypes();
            request.setAttribute("hotelRoomTypes", hotelRoomTypes);

            // Fetch hotels and room types for the form
            request.setAttribute("hotels", hotelDao.getHotels());
            request.setAttribute("roomTypes", roomTypeDao.getRoomTypes());
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching hotel-room-type data: " + e.getMessage());
        }

        request.getRequestDispatcher("/manageHotelRoomTypes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        // If the method is DELETE, handle deletion
        if ("DELETE".equals(method)) {
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));
            int roomTypeId = Integer.parseInt(request.getParameter("roomTypeId"));

            // Perform the delete operation
            try {
                HotelRoomTypeDao hotelRoomTypeDao = new HotelRoomTypeDao();
                hotelRoomTypeDao.deleteHotelRoomType(hotelId, roomTypeId); // Delete the record
                response.sendRedirect("/Proj_war_exploded/manageHotelRoomTypes"); // Redirect back to the manage page
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting hotel-room-type link: " + e.getMessage());
            }
        } else {
            // Get form parameters (hotelId and roomTypeId) for adding
            try {
                int hotelId = Integer.parseInt(request.getParameter("hotelId"));
                int roomTypeId = Integer.parseInt(request.getParameter("roomTypeId"));

                // Create the HotelRoomType object
                HotelRoomType hotelRoomType = new HotelRoomType(hotelId, roomTypeId);

                // Add the hotel-room-type link to the DB
                HotelRoomTypeDao hotelRoomTypeDao = new HotelRoomTypeDao();
                hotelRoomTypeDao.addHotelRoomType(hotelRoomType); // Insert into DB

                // Redirect to manageHotelRoomTypes page
                response.sendRedirect("/Proj_war_exploded/manageHotelRoomTypes");
            } catch (SQLException e) {
                throw new RuntimeException("Error adding hotel-room-type link: " + e.getMessage());
            }
        }
    }
}
