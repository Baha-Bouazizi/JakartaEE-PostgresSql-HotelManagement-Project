<%@ page import="com.example.proj.Models.HotelRoomType" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.proj.Models.Hotel" %>
<%@ page import="com.example.proj.Models.RoomType" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Hotel Room Types</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Poppins', sans-serif;
    }
    .container {
      margin-top: 20px;
    }
    .table-wrapper {
      margin-top: 40px;
    }
    .btn-custom {
      display: flex;
      align-items: center;
      gap: 8px;
    }
    .btn-custom i {
      font-size: 1.2rem;
    }
    .form-section {
      background-color: #f8f9fa;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      margin-bottom: 20px;
    }
    .form-section h3 {
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2 class="text-center my-4">Manage Hotel Room Types</h2>

  <!-- Add Hotel Room Type Form -->
  <div class="form-section">
    <h3>Add Hotel Room Type Link</h3>
    <form action="/Proj_war_exploded/manageHotelRoomTypes" method="POST">
      <div class="mb-3">
        <label for="hotelId" class="form-label">Hotel</label>
        <select class="form-control" id="hotelId" name="hotelId" required>
          <%
            // Fetch hotels from the request attribute (set in controller)
            List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
            for (Hotel hotel : hotels) {
          %>
          <option value="<%= hotel.getId() %>"><%= hotel.getName() %></option>
          <% } %>
        </select>
      </div>
      <div class="mb-3">
        <label for="roomTypeId" class="form-label">Room Type</label>
        <select class="form-control" id="roomTypeId" name="roomTypeId" required>
          <%
            // Fetch room types from the request attribute (set in controller)
            List<RoomType> roomTypes = (List<RoomType>) request.getAttribute("roomTypes");
            for (RoomType roomType : roomTypes) {
          %>
          <option value="<%= roomType.getId() %>"><%= roomType.getLabel() %></option>
          <% } %>
        </select>
      </div>
      <button type="submit" class="btn btn-primary btn-custom">
        <i class="fas fa-plus-circle"></i> Add Link
      </button>
    </form>
  </div>

  <!-- Hotel Room Type Links -->
  <div class="table-wrapper">
    <h3>Existing Hotel Room Type Links</h3>
    <table class="table table-striped table-hover">
      <thead class="table-dark">
      <tr>
        <th>Hotel</th>
        <th>Room Type</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        // Fetch hotel-room-type links from the request attribute (set in controller)
        List<HotelRoomType> hotelRoomTypes = (List<HotelRoomType>) request.getAttribute("hotelRoomTypes");

        for (HotelRoomType hrt : hotelRoomTypes) {
          // Fetch the hotel and room type names using their respective IDs
          String hotelName = hotels.stream()
                  .filter(h -> h.getId() == hrt.getHotelId())
                  .findFirst()
                  .map(Hotel::getName)
                  .orElse("Unknown");

          String roomTypeName = roomTypes.stream()
                  .filter(rt -> rt.getId() == hrt.getRoomTypeId())
                  .findFirst()
                  .map(RoomType::getLabel)
                  .orElse("Unknown");
      %>
      <tr>
        <td><%= hotelName %></td>
        <td><%= roomTypeName %></td>
        <td>
          <form action="/Proj_war_exploded/manageHotelRoomTypes" method="POST" style="display:inline;">
            <input type="hidden" name="_method" value="DELETE">
            <input type="hidden" name="hotelId" value="<%= hrt.getHotelId() %>">
            <input type="hidden" name="roomTypeId" value="<%= hrt.getRoomTypeId() %>">
            <button type="submit" class="btn btn-danger btn-sm btn-custom">
              <i class="fas fa-trash"></i> Delete
            </button>
          </form>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>
