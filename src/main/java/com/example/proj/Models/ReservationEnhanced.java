package com.example.proj.Models;

public class ReservationEnhanced {
    private String roomName;
    private String hotelName;
    private double totalAmount;

    public ReservationEnhanced(String roomName, String hotelName, double totalAmount) {
        this.roomName = roomName;
        this.hotelName = hotelName;
        this.totalAmount = totalAmount;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
