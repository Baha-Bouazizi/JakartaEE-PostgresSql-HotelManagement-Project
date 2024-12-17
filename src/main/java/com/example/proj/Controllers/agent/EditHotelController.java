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

@WebServlet("/editHotel")
@MultipartConfig
public class EditHotelController extends HttpServlet {

    // Handle GET request to show the edit form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HotelDao hotelDao = new HotelDao();

        try {
            // Retrieve the hotel by its ID
            Hotel hotel = hotelDao.getHotelById(id);
            request.setAttribute("hotel", hotel);
            request.getRequestDispatcher("/editHotel.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving hotel: " + e.getMessage());
        }
    }

    // Handle POST request to update hotel details
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        int stars = Integer.parseInt(request.getParameter("stars"));
        String description = request.getParameter("description");

        // Handle image upload
        Part filePart = request.getPart("image");
        String imagePath = null;

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            String uploadDir = getServletContext().getRealPath("/") + "uploads/images/";
            File uploadFolder = new File(uploadDir);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String filePath = uploadDir + fileName;
            filePart.write(filePath);

            // Set the new image path
            imagePath = "/uploads/images/" + fileName;
        }

        // Update hotel details
        Hotel hotel = new Hotel(id, name, city, stars, description, imagePath);
        HotelDao hotelDao = new HotelDao();

        try {
            hotelDao.updateHotel(hotel);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating hotel: " + e.getMessage());
        }

        response.sendRedirect("/Proj_war_exploded/manageHotel");
    }
}
