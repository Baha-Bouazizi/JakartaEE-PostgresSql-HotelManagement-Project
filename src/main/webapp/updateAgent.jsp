<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proj.Models.Account" %>
<html>
<head>
  <title>Update Agent</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2 class="my-4">Update Agent</h2>
  <form action="/Proj_war_exploded/updateAgent" method="POST">
    <input type="hidden" name="username" value="<%= ((Account) request.getAttribute("agent")).getUsername() %>">
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" id="email" name="email" required value="<%= ((Account) request.getAttribute("agent")).getEmail() %>">
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
