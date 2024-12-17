<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proj.Models.Hotel" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Hotel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2>Edit Hotel</h2>

  <form action="/Proj_war_exploded/editHotel" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%= ((Hotel) request.getAttribute("hotel")).getId() %>">
    <div class="mb-3">
      <label for="name" class="form-label">Hotel Name</label>
      <input type="text" class="form-control" id="name" name="name" value="<%= ((Hotel) request.getAttribute("hotel")).getName() %>" required>
    </div>
    <div class="mb-3">
      <label for="city" class="form-label">City</label>
      <input type="text" class="form-control" id="city" name="city" value="<%= ((Hotel) request.getAttribute("hotel")).getCity() %>" required>
    </div>
    <div class="mb-3">
      <label for="stars" class="form-label">Stars (1-5)</label>
      <input type="number" class="form-control" id="stars" name="stars" value="<%= ((Hotel) request.getAttribute("hotel")).getStars() %>" min="1" max="5" required>
    </div>
    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" id="description" name="description" rows="3" required><%= ((Hotel) request.getAttribute("hotel")).getDescription() %></textarea>
    </div>
    <div class="mb-3">
      <label for="image" class="form-label">Hotel Image</label>
      <input type="file" class="form-control" id="image" name="image">
      <p>Current Image: <img src="<%= request.getContextPath() + ((Hotel) request.getAttribute("hotel")).getImagePath() %>" width="100"></p>
    </div>
    <button type="submit" class="btn btn-primary">Update Hotel</button>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
