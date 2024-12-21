package com.example.proj.Models;

public class RoomType {
    private int id;
    private String label;
    private int capacity;

    // Constructor
    public RoomType(int id, String label, int capacity) {
        this.id = id;
        this.label = label;
        this.capacity = capacity;
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
}
