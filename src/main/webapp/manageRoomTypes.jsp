<%@ page import="com.example.proj.Models.RoomType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Room Types</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Google Font (Poppins) -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Poppins', sans-serif;
    }
    .container {
      margin-top: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Manage Room Types</h2>

  <!-- Add Room Type Form -->
  <form action="/Proj_war_exploded/manageRoomTypes" method="POST">
    <div class="mb-3">
      <label for="label" class="form-label">Room Type Label</label>
      <select class="form-control" id="label" name="label" required>
        <option value="Standard Room">Standard Room</option>
        <option value="Deluxe Room">Deluxe Room</option>
        <option value="Suite">Suite</option>
        <option value="Executive Room">Executive Room</option>
        <option value="Family Room">Family Room</option>
        <option value="Single Room">Single Room</option>
        <option value="Double Room">Double Room</option>
        <option value="Twin Room">Twin Room</option>
        <option value="Penthouse">Penthouse</option>
        <option value="Bungalow">Bungalow</option>
        <option value="Poolside Room">Poolside Room</option>
        <option value="Ocean View Room">Ocean View Room</option>
        <option value="Mountain View Room">Mountain View Room</option>
        <option value="Accessible Room">Accessible Room</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="capacity" class="form-label">Capacity</label>
      <input type="number" class="form-control" id="capacity" name="capacity" required>
    </div>
    <button type="submit" class="btn btn-primary">Add Room Type</button>
  </form>

  <hr>

  <!-- Display Existing Room Types -->
  <h3>Existing Room Types</h3>
  <table class="table">
    <thead>
    <tr>
      <th>Label</th>
      <th>Capacity</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through the roomTypes list using EL -->
    <%
      List<RoomType> roomTypes = (List<RoomType>) request.getAttribute("roomTypes");
      if (roomTypes != null) {
        for (RoomType roomType : roomTypes) {
    %>
    <tr id="roomType_<%= roomType.getId() %>">
      <td id="label_<%= roomType.getId() %>"><%= roomType.getLabel() %></td>
      <td id="capacity_<%= roomType.getId() %>"><%= roomType.getCapacity() %></td>
      <td>
        <!-- Edit button that shows the form in place -->
        <button onclick="editRoomType(<%= roomType.getId() %>);" class="btn btn-warning btn-sm">Edit</button>
        <!-- Delete button -->
        <form action="/Proj_war_exploded/manageRoomTypes" method="POST" style="display:inline;">
          <input type="hidden" name="id" value="<%= roomType.getId() %>">  <!-- Ensure the ID is passed -->
          <button type="submit" class="btn btn-danger btn-sm" name="_method" value="DELETE">Delete</button>
        </form>
      </td>
    </tr>
    <tr id="editRow_<%= roomType.getId() %>" style="display:none;">
      <form action="/Proj_war_exploded/manageRoomTypes" method="POST">
        <input type="hidden" name="id" value="<%= roomType.getId() %>">
        <td><input type="text" name="label" value="<%= roomType.getLabel() %>" class="form-control" required></td>
        <td><input type="number" name="capacity" value="<%= roomType.getCapacity() %>" class="form-control" required></td>
        <td><button type="submit" class="btn btn-success btn-sm">Save</button></td>
      </form>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function editRoomType(id) {
    // Toggle the visibility of the edit form and hide the static row
    var editRow = document.getElementById('editRow_' + id);
    var staticRow = document.getElementById('roomType_' + id);

    if (editRow.style.display === "none" || editRow.style.display === "") {
      editRow.style.display = "table-row";  // Show the edit form
      staticRow.style.display = "none";     // Hide the static data
    } else {
      editRow.style.display = "none";       // Hide the edit form
      staticRow.style.display = "table-row"; // Show the static data
    }
  }
</script>
</body>
</html>
