<%@ page import="com.example.proj.Models.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - Hotel Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background-color: #f8f9fa;
    }
    .navbar {
      background-color: #343a40;
      padding: 10px 20px;
    }
    .navbar a {
      color: white;
      text-decoration: none;
      margin-left: 20px;
      font-size: 16px;
    }
    .navbar a:hover {
      color: #ffc107;
    }
    .hero {
      text-align: center;
      padding: 50px 0;
      background: linear-gradient(to bottom right, #6a11cb, #2575fc);
      color: white;
    }
    .hero h1 {
      font-size: 2.5rem;
      margin-bottom: 20px;
    }
    .container {
      margin-top: 30px;
    }
    .card img {
      height: 200px;
      object-fit: cover;
    }
    .footer {
      margin-top: 50px;
      text-align: center;
      padding: 20px;
      background-color: #343a40;
      color: white;
    }
  </style>
</head>
<body>
<!-- Top Bar -->
<nav class="navbar navbar-expand-lg">
  <a href="#" class="navbar-brand text-white">Hotel Management</a>
  <div class="ms-auto">
    <a href="/Proj_war_exploded/login">Login</a>
    <a href="/Proj_war_exploded/register">Sign Up</a>
  </div>
</nav>

<!-- Hero Section -->
<div class="hero">
  <h1>Welcome to Our Hotel Booking Platform</h1>
  <p>Find and book the best hotels at the best prices!</p>
</div>

<!-- Hotel Cards -->
<div class="container">
  <div class="row">
    <%
      List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
      if (hotels != null && !hotels.isEmpty()) {
        for (Hotel hotel : hotels) {
    %>
    <div class="col-md-4 mb-4">
      <div class="card">
        <img src="<%= request.getContextPath() + hotel.getImagePath()%>" class="card-img-top" alt="<%= hotel.getName() %>">
        <div class="card-body">
          <h5 class="card-title"><%= hotel.getName() %></h5>
          <p class="card-text"><strong>City:</strong> <%= hotel.getCity() %></p>
          <p class="card-text"><strong>Stars:</strong>
            <%
              for (int i = 0; i < hotel.getStars(); i++) {
            %>
            ★
            <%
              }
            %>
          </p>
          <p class="card-text"><%= hotel.getDescription() %></p>
          <a href="<%= request.getContextPath() + "/showHotelDetails?id=" + hotel.getId() %>"
             class="btn btn-info btn-sm">View Details</a>
        </div>
      </div>
    </div>
    <%
      }
    } else {
    %>
    <p class="text-center">No hotels available.</p>
    <%
      }
    %>
  </div>
</div>

<!-- Footer -->
<div class="footer">
  <p>&copy; 2024 Hotel Management. All Rights Reserved.</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
