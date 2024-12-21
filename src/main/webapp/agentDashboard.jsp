<%@ page import="com.example.proj.Models.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agent Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

  <style>
    body {
      font-family: 'Poppins', sans-serif;
      margin: 0;
      padding: 0;
    }
    .sidebar {
      height: 100vh;
      width: 250px;
      position: fixed;
      top: 0;
      left: 0;
      background-color: #343a40;
      color: white;
      padding-top: 20px;
      display: flex;
      flex-direction: column;
    }
    .sidebar a {
      color: white;
      text-decoration: none;
      font-size: 16px;
      padding: 10px 15px;
      border-radius: 4px;
      transition: all 0.3s;
    }
    .sidebar a:hover {
      background-color: #495057;
      color: #ffffff;
    }
    .sidebar a i {
      margin-right: 10px;
      font-size: 20px;
    }
    .sidebar h3 {
      font-size: 20px;
      font-weight: 600;
      color: #ffffff;
    }
    .sidebar .mt-auto {
      margin-top: auto;
      padding-bottom: 20px;
    }
    .content {
      margin-left: 250px;
      padding: 20px;
    }
    .card {
      margin-bottom: 20px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .card img {
      height: 150px;
      object-fit: cover;
    }
    .stats {
      display: flex;
      justify-content: space-around;
      margin-bottom: 30px;
    }
    .stats .stat {
      background-color: #f8f9fa;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .stats .stat h3 {
      margin-bottom: 5px;
      font-size: 24px;
    }
    .stats .stat p {
      margin: 0;
      font-size: 14px;
      color: #6c757d;
    }
    .star-rating {
      font-size: 1.2rem;
      color: gold;
    }
  </style>
</head>
<body>
<!-- Sidebar -->
<div class="sidebar d-flex flex-column">
  <h3 class="text-center mb-4">Agent Dashboard</h3>
  <a href="/Proj_war_exploded/manageHotel" class="d-flex align-items-center mb-3 px-3">
    <i class="bi bi-building mr-2 pt-100"></i> Manage Hotels
  </a>
  <a href="/Proj_war_exploded/manageRoomTypes" class="d-flex align-items-center mb-3 px-3">
    <i class="bi bi-door-open mr-2"></i> Manage Room Types
  </a>
  <a href="/Proj_war_exploded/manageHotelRoomTypes" class="d-flex align-items-center mb-3 px-3">
    <i class="bi bi-link mr-2"></i> Manage Hotel Room Types
  </a>

  <div class="mt-auto px-3">
    <a href="/Proj_war_exploded/logout" class="d-flex align-items-center text-danger">
      <i class="bi bi-box-arrow-right mr-2"></i> Logout
    </a>
  </div>
</div>

<!-- Content -->
<div class="content">
  <h2>Welcome, <%= request.getAttribute("username") %>!</h2>

  <!-- Statistics Section -->
  <div class="stats">
    <div class="stat">
      <h3><%= request.getAttribute("hotelCount") %></h3>
      <p>Total Hotels</p>
    </div>
    <div class="stat">
      <h3><%= request.getAttribute("roomTypeCount") %></h3>
      <p>Total Room Types</p>
    </div>
  </div>

  <!-- Search Form -->
  <form method="get" action="/Proj_war_exploded/agentDashboard" class="my-4">
    <div class="row">
      <div class="col-md-3">
        <input type="text" name="city" class="form-control" placeholder="City" />
      </div>
      <div class="col-md-3">
        <input type="number" name="stars" class="form-control" placeholder="Stars (1-5)" min="1" max="5"/>
      </div>
      <div class="col-md-3">
        <input type="number" name="priceMin" class="form-control" placeholder="Min Price" />
      </div>
      <div class="col-md-3">
        <input type="number" name="priceMax" class="form-control" placeholder="Max Price" />
      </div>
    </div>
    <button type="submit" class="btn btn-primary mt-3">Search</button>
  </form>

  <!-- Hotel Cards -->
  <div class="hotel-cards row">
    <%
      List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
      if (hotels == null || hotels.isEmpty()) {
    %>
    <p>No hotels available.</p>
    <%
    } else {
      for (Hotel hotel : hotels) {
    %>
    <div class="col-md-3">
      <div class="card">
        <img src="<%= request.getContextPath() + hotel.getImagePath() %>" alt="<%= hotel.getName() %>" class="card-img-top">
        <div class="card-body">
          <h5 class="card-title"><%= hotel.getName() %></h5>
          <p class="card-text"><strong>City:</strong> <%= hotel.getCity() %></p>
          <div class="star-rating">
            <%
              int stars = hotel.getStars();
              for (int i = 0; i < stars; i++) {
            %>
            <i class="fas fa-star"></i>
            <%
              }
            %>
          </div>
          <p class="card-text"><%= hotel.getDescription() %></p>
          <a href="<%= request.getContextPath() + "/showHotelDetails?id=" + hotel.getId() %>" class="btn btn-info btn-sm">View Details</a>
        </div>
      </div>
    </div>
    <%
        }
      }
    %>
  </div>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
