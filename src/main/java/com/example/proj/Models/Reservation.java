package com.example.proj.Models;

public class Reservation {
    private int id;
    private int userId;
    private int hotelId;
    private int roomTypeId;
    private int period; // Period in days
    private String status; // e.g., "Pending", "Confirmed"

    // Constructors
    public Reservation(int id, int userId, int hotelId, int roomTypeId, int period, String status) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.period = period;
        this.status = status;
    }

    public Reservation() {}

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

