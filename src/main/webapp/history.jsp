<%@ page import="com.example.proj.Models.ReservationEnhanced" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reservation History</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1>Reservation History</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Room Name</th>
      <th>Hotel Name</th>
      <th>Total Amount</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<ReservationEnhanced> reservations = (List<ReservationEnhanced>) request.getAttribute("reservations");
      if (reservations != null && !reservations.isEmpty()) {
        for (ReservationEnhanced reservation : reservations) {
    %>
    <tr>
      <td><%= reservation.getRoomName() %></td>
      <td><%= reservation.getHotelName() %></td>
      <td><%= reservation.getTotalAmount() %></td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="3" class="text-center">No reservations found.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <a href="<%= request.getContextPath() %>/userDashboard" class="btn btn-secondary">Back to Dashboard</a>
</div>
</body>
</html>
