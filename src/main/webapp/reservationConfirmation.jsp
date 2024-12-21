<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reservation Confirmation</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Poppins', sans-serif;
      background-color: #f8f9fa;
    }
    .container {
      margin-top: 50px;
    }
    .confirmation-card {
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .btn-history {
      background-color: #007bff;
      color: white;
      border: none;
    }
    .btn-history:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="confirmation-card">
    <h2 class="text-center">Reservation Confirmed</h2>
    <p class="text-center">Your reservation request has been successfully submitted. It is now being processed by our agents. You will receive an update once it is confirmed.</p>
    <p class="text-center">You can check the status of your reservation in your <a href="history.jsp">Reservation History</a>.</p>
    <div class="text-center">
      <a href="history.jsp" class="btn btn-history">Go to Reservation History</a>
    </div>
  </div>
</div>

</body>
</html>
