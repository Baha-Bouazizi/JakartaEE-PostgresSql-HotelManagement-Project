package com.example.proj.dao;

import com.example.proj.Models.RoomType;
import com.example.proj.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao {

    // Method to fetch all room types
    public List<RoomType> getRoomTypes() throws SQLException {
        List<RoomType> roomTypes = new ArrayList<>();
        String query = "SELECT * FROM RoomType";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RoomType roomType = new RoomType(
                        rs.getInt("id"),
                        rs.getString("label"),
                        rs.getInt("capacity"),
                        rs.getDouble("prix")  // Changed to double for price
                );
                roomTypes.add(roomType);
            }
        }

        return roomTypes;
    }

    // Method to add a new room type
    public void addRoomType(RoomType roomType) throws SQLException {
        String query = "INSERT INTO RoomType (label, capacity, prix) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, roomType.getLabel());
            stmt.setInt(2, roomType.getCapacity());
            stmt.setDouble(3, roomType.getPrice());  // Using setDouble for price
            stmt.executeUpdate();
        }
    }

    // Method to fetch a specific room type by ID
    public RoomType getRoomTypeById(int id) throws SQLException {
        String query = "SELECT * FROM RoomType WHERE id = ?";
        RoomType roomType = null;

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                roomType = new RoomType(
                        rs.getInt("id"),
                        rs.getString("label"),
                        rs.getInt("capacity"),
                        rs.getDouble("prix")  // Using getDouble for price
                );
            }
        }

        return roomType;
    }

    // Method to update an existing room type
    public void updateRoomType(RoomType roomType) throws SQLException {
        String query = "UPDATE RoomType SET label = ?, capacity = ?, prix = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, roomType.getLabel());
            stmt.setInt(2, roomType.getCapacity());
            stmt.setDouble(3, roomType.getPrice());  // Using setDouble for price
            stmt.setInt(4, roomType.getId());
            stmt.executeUpdate();
        }
    }

    // Method to delete a room type
    public void deleteRoomType(int id) throws SQLException {
        String query = "DELETE FROM RoomType WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public int getRoomTypeCount() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM RoomType";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count");
            }
        }
        return 0;
    }
}
