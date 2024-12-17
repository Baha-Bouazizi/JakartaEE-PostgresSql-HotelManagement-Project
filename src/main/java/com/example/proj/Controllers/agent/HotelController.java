package com.example.proj.Controllers.agent;

import com.example.proj.Models.Hotel;
import com.example.proj.dao.HotelDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manageHotel")
@MultipartConfig
public class HotelController extends HttpServlet {

    // Handle GET requests (view list of hotels or show the add hotel form)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HotelDao hotelDao = new HotelDao();

        if ("edit".equals(action)) {
            // Fetch hotel by ID and show edit form
            int id = Integer.parseInt(request.getParameter("id"));
            Hotel hotel = null;
            try {
                hotel = hotelDao.getHotelById(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("hotel", hotel);  // Set the hotel to request
            request.getRequestDispatcher("/editHotel.jsp").forward(request, response);  // Forward to editHotel.jsp
        } else {
            // Display list of hotels
            List<Hotel> hotels = null;
            try {
                hotels = hotelDao.getHotels();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("hotels", hotels);  // Set the list of hotels to request
            request.getRequestDispatcher("/manageHotel.jsp").forward(request, response);  // Forward to manageHotel.jsp
        }
    }


    // Handle POST requests (add/edit hotel)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        if ("DELETE".equals(method)) {
            int id = Integer.parseInt(request.getParameter("id"));
            deleteHotel(id);
            response.sendRedirect("/Proj_war_exploded/manageHotel");
        } else {
            // Get hotel details from the form
            String name = request.getParameter("name");
            String city = request.getParameter("city");
            int stars = Integer.parseInt(request.getParameter("stars"));
            String description = request.getParameter("description");

            // Handle image upload
            Part filePart = request.getPart("image"); // Get the image file from the form
            String fileName = filePart.getSubmittedFileName();
            String uploadDir = getServletContext().getRealPath("/") + "uploads/images/"; // Image folder path
            File uploadFolder = new File(uploadDir);

            // Create the folder if it doesn't exist
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Save the uploaded file
            String filePath = uploadDir + fileName;
            filePart.write(filePath);

            // Store the relative path of the image in the database
            String imagePath = "/uploads/images/" + fileName;

            // Save hotel details to the database
            Hotel hotel = new Hotel(0, name, city, stars, description, imagePath);
            HotelDao hotelDao = new HotelDao();
            try {
                hotelDao.addHotel(hotel);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Redirect back to manageHotel page after saving the hotel
            response.sendRedirect("/Proj_war_exploded/manageHotel");
        }
    }

    private void deleteHotel(int id) {
        HotelDao hotelDao = new HotelDao();
        try {
            hotelDao.deleteHotel(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting hotel: " + e.getMessage());
        }
    }
}
