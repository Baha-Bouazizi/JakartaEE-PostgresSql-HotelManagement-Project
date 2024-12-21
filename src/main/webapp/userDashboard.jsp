<%@ page import="com.example.proj.Models.Hotel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard - Hotel Management</title>
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
        .navbar .reservation-btn {
            background-color: #ff6347;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            font-weight: 600;
        }
        .navbar .reservation-btn:hover {
            background-color: #ff4500;
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
        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }
        .card:hover {
            transform: translateY(-10px);
        }
        .card img {
            height: 200px;
            object-fit: cover;
        }
        .card-body {
            background-color: #f9f9f9;
            border-radius: 0 0 10px 10px;
            padding: 20px;
        }
        .card-title {
            font-size: 1.5rem;
            font-weight: 600;
        }
        .card-text {
            font-size: 1rem;
        }
        .btn-reserve {
            background-color: #007bff;
            color: white;
            font-size: 1rem;
            font-weight: 600;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn-reserve:hover {
            background-color: #0056b3;
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
    <div class="ms-auto d-flex align-items-center">
        <%-- History Button --%>
            <a href="<%= request.getContextPath() %>/history?userId=<%= session.getAttribute("userId") %>" class="btn btn-primary me-3">
                <i class="bi bi-clock-history"></i> History
            </a>

        <%-- Profile Dropdown --%>
        <c:if test="${not empty sessionScope.username}">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi bi-person-circle"></i> ${sessionScope.username}
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item" href="/Proj_war_exploded/profile">Profile</a></li>
                    <li><a class="dropdown-item" href="/Proj_war_exploded/logout">Logout</a></li>
                </ul>
            </div>
        </c:if>
    </div>
</nav>


<!-- Hero Section -->
<div class="hero">
    <h1>Welcome to Your Dashboard</h1>
    <p>Browse and manage your reservations!</p>
</div>

<!-- Hotel Cards -->
<div class="container">
    <h2>Your Available Hotels</h2>
    <div class="row">
        <%
            List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
            if (hotels != null && !hotels.isEmpty()) {
                for (Hotel hotel : hotels) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="<%= request.getContextPath() + hotel.getImagePath() %>" class="card-img-top" alt="<%= hotel.getName() %>">
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
                    <a href="<%= request.getContextPath() + "/showHotelDetails?id=" + hotel.getId() %>" class="btn btn-info btn-sm">View Details</a>
                    <a href="<%= request.getContextPath() + "/showHotelDetailsReserve?id=" + hotel.getId() %>" class="btn btn-info btn-sm">
                        <i class="bi bi-check-lg"></i> Reserve
                    </a>
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

<!-- Bootstrap JS & Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.js"></script>
</body>
</html>
