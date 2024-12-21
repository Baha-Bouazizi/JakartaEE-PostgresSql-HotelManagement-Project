package com.example.proj.Controllers.user;


import com.example.proj.Models.Hotel;
import com.example.proj.Models.HotelRoomType;
import com.example.proj.Models.Reservation;
import com.example.proj.Models.RoomType;
import com.example.proj.dao.HotelDao;
import com.example.proj.dao.HotelRoomTypeDao;
import com.example.proj.dao.ReservationDao;
import com.example.proj.dao.RoomTypeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showHotelDetailsReserve")
public class ShowHotelDetailsReserve extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hotelId = Integer.parseInt(request.getParameter("id")); // Get the hotel ID from the request
        System.out.println("hotelId from GET request: " + hotelId);

        HotelDao hotelDao = new HotelDao();
        RoomTypeDao roomTypeDao = new RoomTypeDao();
        HotelRoomTypeDao hotelRoomTypeDao = new HotelRoomTypeDao();

        try {
            // Fetch hotel details by ID
            Hotel hotel = hotelDao.getHotelById(hotelId);
            System.out.println("Hotel fetched: " + hotel);

            // Fetch room types associated with the hotel
            List<RoomType> roomTypes = roomTypeDao.getRoomTypes();
            System.out.println("Room Types fetched: " + roomTypes);

            // Fetch the number of each room type available in the hotel
            List<HotelRoomType> hotelRoomTypes = hotelRoomTypeDao.getHotelRoomTypesForHotel(hotelId);
            System.out.println("Hotel Room Types fetched: " + hotelRoomTypes);

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
        System.out.println("Forwarding to ShowhotelDetailsReserve.jsp");
        request.getRequestDispatcher("/ShowhotelDetailsReserve.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            System.out.println("Session ID: " + session.getId());

            // Retrieve userId from session (stored as a String)
            String userIdString = (String) session.getAttribute("userId");

            // Log the incoming parameters
            System.out.println("Incoming parameters:");
            System.out.println("hotelId: " + request.getParameter("hotelId"));
            System.out.println("roomTypeId: " + request.getParameter("roomTypeId"));
            System.out.println("period: " + request.getParameter("period"));

            String hotelIdString = request.getParameter("hotelId");
            String roomTypeIdString = request.getParameter("roomTypeId");
            String periodString = request.getParameter("period");

            if (hotelIdString == null || roomTypeIdString == null || periodString == null) {
                response.getWriter().write("Missing required parameters.");
                return;
            }

            // Debugging: Before parsing
            System.out.println("Attempting to parse values...");

            int hotelId = Integer.parseInt(hotelIdString);  // Convert to Integer
            int roomTypeId = Integer.parseInt(roomTypeIdString);  // Convert to Integer
            int period = Integer.parseInt(periodString);  // Convert to Integer

            // Log parsed values
            System.out.println("Parsed hotelId: " + hotelId);
            System.out.println("Parsed roomTypeId: " + roomTypeId);
            System.out.println("Parsed period: " + period);



            if (userIdString == null) {
                System.out.println("userId is NULL in the session");
                response.sendRedirect("login.jsp");
                return;
            }

            // Convert userId to Integer from String
            Integer userId = Integer.parseInt(userIdString);  // Convert userId from String to Integer
            System.out.println("userId from session: " + userId);

            // Create a reservation
            ReservationDao reservationDao = new ReservationDao();
            Reservation reservation = new Reservation();
            reservation.setUserId(userId);
            reservation.setHotelId(hotelId);
            reservation.setRoomTypeId(roomTypeId);
            reservation.setPeriod(period);
            reservation.setStatus("Pending");

            // Debugging: Check the reservation details before saving
            System.out.println("Reservation to save: " + reservation);

            // Save the reservation
            boolean isSaved = reservationDao.addReservation(reservation);
            System.out.println("Reservation saved: " + isSaved);

            if (isSaved) {
                response.sendRedirect("reservationConfirmation.jsp"); // Redirect to a confirmation page
                System.out.println("Redirecting to reservationConfirmation.jsp");
            } else {
                response.getWriter().write("Error saving reservation");
                System.out.println("Error saving reservation");
            }
        } catch (NumberFormatException e) {
            // Log the exception
            e.printStackTrace();
            response.getWriter().write("Invalid parameter format: " + e.getMessage());
            System.out.println("NumberFormatException: " + e.getMessage());
        }
    }


}
