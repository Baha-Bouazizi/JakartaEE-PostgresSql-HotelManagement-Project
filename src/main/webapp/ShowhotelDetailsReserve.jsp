<%@ page import="com.example.proj.Models.Hotel" %>
<%@ page import="com.example.proj.Models.RoomType" %>
<%@ page import="com.example.proj.Models.HotelRoomType" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hotel Details</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Google Font (Poppins) -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

  <style>
    body {
      font-family: 'Poppins', sans-serif;
    }
    .container {
      margin-top: 20px;
    }
    .hotel-info {
      margin-bottom: 40px;
    }
    .room-type {
      margin-bottom: 20px;
      cursor: pointer;
      border: 2px solid transparent;
      border-radius: 5px;
      padding: 15px;
      transition: border-color 0.3s;
    }
    .room-type.selected {
      border-color: #007bff;
    }
    .card-img-top {
      height: 200px;
      object-fit: cover;
    }
    .star-rating {
      color: gold;
      font-size: 1.5rem;
    }
  </style>
</head>
<body>

<div class="container">
  <h2 class="text-center my-4">Hotel Details: <%= ((Hotel) request.getAttribute("hotel")).getName() %></h2>

  <!-- Hotel Information -->
  <div class="hotel-info">
    <div class="card">
      <img src="<%= request.getContextPath() + ((Hotel) request.getAttribute("hotel")).getImagePath() %>" alt="<%= ((Hotel) request.getAttribute("hotel")).getName() %>" class="card-img-top">
      <div class="card-body">
        <h5 class="card-title"><%= ((Hotel) request.getAttribute("hotel")).getName() %></h5>
        <p class="card-text"><strong>City:</strong> <%= ((Hotel) request.getAttribute("hotel")).getCity() %></p>
        <p class="card-text"><strong>Stars:</strong>
          <%
            int stars = ((Hotel) request.getAttribute("hotel")).getStars();
            for (int i = 0; i < stars; i++) {
          %>
          <i class="bi bi-star-fill"></i>
          <%
            }
          %>
        </p>
        <p class="card-text"><strong>Description:</strong> <%= ((Hotel) request.getAttribute("hotel")).getDescription() %></p>
      </div>
    </div>
  </div>



  <!-- Room Types -->
  <h3>Select Room Type</h3>
  <form action="showHotelDetailsReserve" method="post">
    <input type="hidden" name="hotelId" value="<%= ((Hotel) request.getAttribute("hotel")).getId() %>">

    <div class="row">
      <%
        List<HotelRoomType> hotelRoomTypes = (List<HotelRoomType>) request.getAttribute("hotelRoomTypes");
        List<RoomType> roomTypes = (List<RoomType>) request.getAttribute("roomTypes");

        for (HotelRoomType hotelRoomType : hotelRoomTypes) {
          RoomType roomType = roomTypes.stream()
                  .filter(rt -> rt.getId() == hotelRoomType.getRoomTypeId())
                  .findFirst()
                  .orElse(null);
          if (roomType != null) {
      %>
      <div class="col-md-4">
        <div class="card room-type" id="roomType_<%= roomType.getId() %>" onclick="selectRoomType(<%= roomType.getId() %>)">
          <div class="card-body">
            <h5 class="card-title"><%= roomType.getLabel() %></h5>
            <p class="card-text"><strong>Capacity:</strong> <%= roomType.getCapacity() %></p>
          </div>
        </div>
        <input type="radio" name="roomTypeId" value="<%= roomType.getId() %>" id="radio_<%= roomType.getId() %>" style="display: none;">
      </div>
      <%
          }
        }
      %>
    </div>

    <!-- Period Input -->
    <div class="mb-3">
      <label for="period" class="form-label">Period (in days):</label>
      <input type="number" class="form-control" id="period" name="period" required min="1">
    </div>

    <!-- Reserve Button -->
    <button type="submit" class="btn btn-primary">Reserve</button>
  </form>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  function selectRoomType(roomTypeId) {
    // Deselect all room types
    const allRoomTypes = document.querySelectorAll('.room-type');
    allRoomTypes.forEach(card => card.classList.remove('selected'));

    // Select the clicked room type card
    const selectedCard = document.getElementById('roomType_' + roomTypeId);
    selectedCard.classList.add('selected');

    // Check the corresponding radio button
    const radioButton = document.getElementById('radio_' + roomTypeId);
    radioButton.checked = true;
  }
</script>
</body>
</html>
