package com.example.proj.Models;

public class HotelRoomType {
    private int hotelId;
    private int roomTypeId;
    private double price;  // Added price
    private int quantity;  // Added quantity

    public HotelRoomType(int hotelId, int roomTypeId, double price, int quantity) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
