package com.example.proj.Controllers.agent;

import com.example.proj.Models.RoomType;
import com.example.proj.dao.RoomTypeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manageRoomTypes")
public class RoomTypeController extends HttpServlet {

    // Handles both viewing all room types and showing the form for adding/editing
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            // If action is "edit", show the edit form with room type details
            int id = Integer.parseInt(request.getParameter("id"));
            RoomTypeDao roomTypeDao = new RoomTypeDao();
            try {
                RoomType roomType = roomTypeDao.getRoomTypeById(id);  // Fetch room type by ID
                request.setAttribute("roomType", roomType);  // Set room type in request
                request.getRequestDispatcher("/manageRoomTypes.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error fetching room type: " + e.getMessage());
            }
        } else {
            // If no action or "list" action, show all room types
            RoomTypeDao roomTypeDao = new RoomTypeDao();
            try {
                List<RoomType> roomTypes = roomTypeDao.getRoomTypes();  // Fetch all room types
                request.setAttribute("roomTypes", roomTypes);  // Set room types in the request
                request.getRequestDispatcher("/manageRoomTypes.jsp").forward(request, response);  // Forward to the same JSP for listing
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error fetching room types: " + e.getMessage());
            }
        }
    }

    // Handles adding or updating room types (depending on whether ID is provided)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if ("DELETE".equals(method)) {
            // Handle delete request
            int id = Integer.parseInt(request.getParameter("id"));  // Get the room type ID from the form
            RoomTypeDao roomTypeDao = new RoomTypeDao();
            try {
                roomTypeDao.deleteRoomType(id);  // Call the DAO to delete the room type
                response.sendRedirect("/Proj_war_exploded/manageRoomTypes");  // Redirect back to the room types management page
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error deleting room type: " + e.getMessage());
            }
        } else {
            // Add or update room type logic (handled before)
            int id = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "0");
            String label = request.getParameter("label");
            int capacity = Integer.parseInt(request.getParameter("capacity"));

            RoomType roomType = new RoomType(id, label, capacity);

            RoomTypeDao roomTypeDao = new RoomTypeDao();

            try {
                if (id == 0) {
                    // Add new room type
                    roomTypeDao.addRoomType(roomType);  // Add room type to database
                } else {
                    // Update existing room type
                    roomTypeDao.updateRoomType(roomType);  // Update room type in database
                }
                response.sendRedirect("/Proj_war_exploded/manageRoomTypes");  // Redirect back to the room types management page
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error saving room type: " + e.getMessage());
            }
        }
    }
}
