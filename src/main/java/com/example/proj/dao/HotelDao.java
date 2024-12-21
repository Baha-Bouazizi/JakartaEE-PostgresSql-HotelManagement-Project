package com.example.proj.dao;

import com.example.proj.Models.Hotel;
import com.example.proj.Models.HotelRoomType;
import com.example.proj.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao {

    // Method to get all hotels from the database
    public List<Hotel> getHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotel";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getInt("stars"),
                        rs.getString("description"),
                        rs.getString("image")
                );
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    // Method to add a hotel to the database
    public void addHotel(Hotel hotel) throws SQLException {
        String query = "INSERT INTO Hotel (name, city, stars, description, image) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getCity());
            stmt.setInt(3, hotel.getStars());
            stmt.setString(4, hotel.getDescription());
            stmt.setString(5, hotel.getImagePath());
            stmt.executeUpdate();
        }
    }

    // Method to get a specific hotel by ID
    public Hotel getHotelById(int id) throws SQLException {
        String query = "SELECT * FROM Hotel WHERE id = ?";
        Hotel hotel = null;
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                hotel = new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getInt("stars"),
                        rs.getString("description"),
                        rs.getString("image")
                );
            }
        }
        return hotel;
    }
    public void deleteHotel(int id) throws SQLException {
        String query = "DELETE FROM Hotel WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);  // Set the ID of the hotel to be deleted
            stmt.executeUpdate();
        }
    }

    public void updateHotel(Hotel hotel) throws SQLException {
        String query = "UPDATE Hotel SET name = ?, city = ?, stars = ?, description = ?, image = COALESCE(?, image) WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getCity());
            stmt.setInt(3, hotel.getStars());
            stmt.setString(4, hotel.getDescription());
            stmt.setString(5, hotel.getImagePath()); // If no new image, keep the existing one
            stmt.setInt(6, hotel.getId());
            stmt.executeUpdate();
        }
    }
    // Method to get the count of hotels
    public int getHotelCount() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM Hotel";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count");
            }
        }
        return 0;
    }
    public List<Hotel> getHotelsFiltered(String city, String stars, String priceMin, String priceMax) throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotel WHERE 1=1";  // Start with a base query

        // Add conditions to the query based on the parameters
        if (city != null && !city.isEmpty()) {
            query += " AND city LIKE ?";
        }
        if (stars != null && !stars.isEmpty()) {
            query += " AND stars = ?";
        }
        if (priceMin != null && !priceMin.isEmpty()) {
            query += " AND id IN (SELECT hotel_id FROM HotelRoomType WHERE price >= ?)";
        }
        if (priceMax != null && !priceMax.isEmpty()) {
            query += " AND id IN (SELECT hotel_id FROM HotelRoomType WHERE price <= ?)";
        }

        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int index = 1;
            // Set parameters for each condition
            if (city != null && !city.isEmpty()) {
                stmt.setString(index++, "%" + city + "%");
            }
            if (stars != null && !stars.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(stars));
            }
            if (priceMin != null && !priceMin.isEmpty()) {
                stmt.setDouble(index++, Double.parseDouble(priceMin));
            }
            if (priceMax != null && !priceMax.isEmpty()) {
                stmt.setDouble(index++, Double.parseDouble(priceMax));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("city"),
                            rs.getInt("stars"),
                            rs.getString("description"),
                            rs.getString("image")
                    );
                    hotels.add(hotel);
                }
            }
        }

        return hotels;
    }






}
