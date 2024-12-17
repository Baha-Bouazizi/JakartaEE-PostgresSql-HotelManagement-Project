<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Admin Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: Arial, sans-serif;
    }

    .sidebar {
      height: 100%;
      width: 250px;
      position: fixed;
      top: 0;
      left: 0;
      background-color: #343a40;
      padding-top: 20px;
    }

    .sidebar a {
      color: white;
      padding: 10px 15px;
      text-decoration: none;
      display: block;
      font-size: 18px;
    }

    .sidebar a:hover {
      background-color: #575757;
    }

    .content {
      margin-left: 250px;
      padding: 20px;
    }

    .content h2 {
      font-size: 28px;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<div class="sidebar">
  <h3 class="text-white text-center">Admin Dashboard</h3>
  <a href="/Proj_war_exploded/adminDashboard">Dashboard</a>
  <a href="/Proj_war_exploded/listAgent">List Agents</a>
  <a href="/Proj_war_exploded/createAgent">Create Agent</a>
  <a href="/Proj_war_exploded/logout">Logout</a>
</div>

<div class="content">
  <h2>Welcome to the Admin Dashboard</h2>
  <!-- Content based on the current page -->
  <div id="contentArea">
    <!-- Default content will go here -->
    <p>Select an option from the sidebar to manage agents.</p>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
