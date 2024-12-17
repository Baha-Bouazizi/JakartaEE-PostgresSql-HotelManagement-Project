package com.example.proj.Models;

public class HotelRoomType {
    private int hotelId;
    private int roomTypeId;

    public HotelRoomType( int hotelId, int roomTypeId) {
        this.hotelId = hotelId;
        this.roomTypeId = roomTypeId;
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
}
