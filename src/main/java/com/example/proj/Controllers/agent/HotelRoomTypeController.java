package com.example.proj.Controllers.agent;

import com.example.proj.Models.HotelRoomType;
import com.example.proj.dao.HotelRoomTypeDao;
import com.example.proj.dao.HotelDao;
import com.example.proj.dao.RoomTypeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manageHotelRoomTypes")
public class HotelRoomTypeController extends HttpServlet {

    // Handles both viewing all room types and showing the form for adding/editing
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelRoomTypeDao dao = new HotelRoomTypeDao();
        HotelDao hotelDao = new HotelDao();
        RoomTypeDao roomTypeDao = new RoomTypeDao();

        try {
            List<HotelRoomType> hotelRoomTypes = dao.getHotelRoomTypes();
            request.setAttribute("hotelRoomTypes", hotelRoomTypes);

            request.setAttribute("hotels", hotelDao.getHotels());
            request.setAttribute("roomTypes", roomTypeDao.getRoomTypes());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error fetching hotel-room-type data: " + e.getMessage());
        }

        request.getRequestDispatcher("/manageHotelRoomTypes.jsp").forward(request, response);
    }

    // Handles adding or updating room types (depending on whether ID is provided)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if ("DELETE".equals(method)) {
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));
            int roomTypeId = Integer.parseInt(request.getParameter("roomTypeId"));
            HotelRoomTypeDao hotelRoomTypeDao = new HotelRoomTypeDao();
            try {
                hotelRoomTypeDao.deleteHotelRoomType(hotelId, roomTypeId);
                response.sendRedirect("/Proj_war_exploded/manageHotelRoomTypes");
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error deleting hotel-room-type link: " + e.getMessage());
            }
        } else {
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));
            int roomTypeId = Integer.parseInt(request.getParameter("roomTypeId"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            HotelRoomType hotelRoomType = new HotelRoomType(hotelId, roomTypeId, price, quantity);

            HotelRoomTypeDao hotelRoomTypeDao = new HotelRoomTypeDao();

            try {
                hotelRoomTypeDao.addHotelRoomType(hotelRoomType); // Add new room type with price and quantity
                response.sendRedirect("/Proj_war_exploded/manageHotelRoomTypes");
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error saving hotel-room-type link: " + e.getMessage());
            }
        }
    }
}
