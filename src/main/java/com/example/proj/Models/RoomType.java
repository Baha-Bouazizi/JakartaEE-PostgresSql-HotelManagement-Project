package com.example.proj.Models;

public class RoomType {
    private int id;
    private String label;
    private int capacity;
    private double price;  // Changed from int to double for fractional prices

    // Constructor
    public RoomType(int id, String label, int capacity, double price) {
        this.id = id;
        this.label = label;
        this.capacity = capacity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
