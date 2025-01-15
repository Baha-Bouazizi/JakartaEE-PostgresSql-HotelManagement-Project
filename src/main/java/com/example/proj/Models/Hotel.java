package com.example.proj.Models;

public class Hotel implements serializable{
    private int id;
    private String name;
    private String city;
    private int stars;  // Stars (1-5)
    private String description;
    private String imagePath;  // Path to the uploaded image

    // Constructor
    public Hotel(int id, String name, String city, int stars, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

