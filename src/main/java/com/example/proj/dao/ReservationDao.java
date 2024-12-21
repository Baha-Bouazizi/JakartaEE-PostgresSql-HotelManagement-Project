package com.example.proj.dao;

import com.example.proj.Models.Reservation;
import com.example.proj.Models.ReservationEnhanced;
import com.example.proj.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    // Fetch reservations by user
    public List<Reservation> getReservationsByUser(int userId) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("room_type_id"),
                        rs.getInt("period"),
                        rs.getString("status")
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Add a new reservation
    public boolean addReservation(Reservation reservation) {
        String query = "INSERT INTO reservation (user_id, hotel_id, room_type_id, period, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getHotelId());
            stmt.setInt(3, reservation.getRoomTypeId());
            stmt.setInt(4, reservation.getPeriod());
            stmt.setString(5, reservation.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update reservation status
    public boolean updateReservationStatus(int reservationId, String status) {
        String query = "UPDATE reservation SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, reservationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<ReservationEnhanced> getReservationsByUserId(int userId) {
        String sql = "SELECT r.room_name, h.hotel_name, r.price * res.period AS total_amount " +
                "FROM reservations res " +
                "JOIN rooms r ON res.room_id = r.room_id " +
                "JOIN hotels h ON r.hotel_id = h.hotel_id " +
                "WHERE res.user_id = ?";

        List<ReservationEnhanced> reservations = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String roomName = rs.getString("room_name");
                String hotelName = rs.getString("hotel_name");
                double totalAmount = rs.getDouble("total_amount");

                ReservationEnhanced reservation = new ReservationEnhanced(roomName, hotelName, totalAmount);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

}
