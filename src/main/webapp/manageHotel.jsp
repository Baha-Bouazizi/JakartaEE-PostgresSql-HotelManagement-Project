<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proj.Models.Hotel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Hotels</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2>Manage Hotels</h2>

  <!-- Add Hotel Form -->
  <form action="/Proj_war_exploded/manageHotel" method="POST" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="name" class="form-label">Hotel Name</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="mb-3">
      <label for="city" class="form-label">City</label>
      <input type="text" class="form-control" id="city" name="city" required>
    </div>
    <div class="mb-3">
      <label for="stars" class="form-label">Stars (1-5)</label>
      <input type="number" class="form-control" id="stars" name="stars" min="1" max="5" required>
    </div>
    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
    </div>
    <div class="mb-3">
      <label for="image" class="form-label">Hotel Image</label>
      <input type="file" class="form-control" id="image" name="image" required>
    </div>
    <button type="submit" class="btn btn-primary">Add Hotel</button>
  </form>

  <hr>

  <!-- List Hotels -->
  <h3>Existing Hotels</h3>
  <table class="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>City</th>
      <th>Stars</th>
      <th>Image</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
      for (Hotel hotel : hotels) {
    %>
    <tr>
      <td><%= hotel.getName() %></td>
      <td><%= hotel.getCity() %></td>
      <td><%= hotel.getStars() %></td>
      <td><img src="<%= request.getContextPath() + hotel.getImagePath() %>" alt="<%= hotel.getName() %>" width="100"></td>
      <td>
        <a href="/Proj_war_exploded/editHotel?id=<%= hotel.getId() %>" class="btn btn-warning btn-sm">Edit</a>

        <!-- Delete Button -->
        <form action="/Proj_war_exploded/manageHotel" method="POST" style="display:inline;">
          <input type="hidden" name="id" value="<%= hotel.getId() %>">
          <button type="submit" class="btn btn-danger btn-sm" name="_method" value="DELETE">Delete</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
