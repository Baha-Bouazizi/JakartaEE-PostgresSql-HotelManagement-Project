package com.example.proj.dao;

import com.example.proj.Models.HotelRoomType;
import com.example.proj.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRoomTypeDao {

    // Method to add a hotel-room-type link with price and quantity
    public void addHotelRoomType(HotelRoomType hotelRoomType) throws SQLException {
        String query = "INSERT INTO HotelRoomType (hotel_id, roomtype_id, price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hotelRoomType.getHotelId());
            stmt.setInt(2, hotelRoomType.getRoomTypeId());
            stmt.setDouble(3, hotelRoomType.getPrice());  // Set the price
            stmt.setInt(4, hotelRoomType.getQuantity());  // Set the quantity

            stmt.executeUpdate();
        }
    }

    // Method to retrieve all hotel-room-type links with price and quantity
    public List<HotelRoomType> getHotelRoomTypes() throws SQLException {
        List<HotelRoomType> hotelRoomTypes = new ArrayList<>();
        String query = "SELECT * FROM HotelRoomType";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HotelRoomType hotelRoomType = new HotelRoomType(
                        rs.getInt("hotel_id"),
                        rs.getInt("roomtype_id"),
                        rs.getDouble("price"),  // Get the price
                        rs.getInt("quantity")   // Get the quantity
                );
                hotelRoomTypes.add(hotelRoomType);
            }
        }

        return hotelRoomTypes;
    }

    // Method to delete a hotel-room-type link by hotel_id and roomtype_id
    public void deleteHotelRoomType(int hotelId, int roomTypeId) throws SQLException {
        String query = "DELETE FROM HotelRoomType WHERE hotel_id = ? AND roomtype_id = ?";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hotelId);
            stmt.setInt(2, roomTypeId);
            stmt.executeUpdate();
        }
    }

    // Method to count the number of hotel-room-type links
    public int getHotelRoomTypeCount() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM HotelRoomType";
        int count = 0;

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("count");
            }
        }

        return count;
    }
    public List<HotelRoomType> getHotelRoomTypesForHotel(int hotelId) throws SQLException {
        List<HotelRoomType> hotelRoomTypes = new ArrayList<>();
        String query = "SELECT * FROM HotelRoomType WHERE hotel_id = ?";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int roomTypeId = rs.getInt("roomtype_id");
                int quantity = rs.getInt("quantity");
                double price=rs.getDouble("price") ;// Fetch quantity
                hotelRoomTypes.add(new HotelRoomType(hotelId, roomTypeId,price ,quantity));
            }
        }
        return hotelRoomTypes;
    }
}
