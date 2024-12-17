<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.proj.Models.Account" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <title>List Agents</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2 class="my-4">List of Agents</h2>
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th>Username</th>
      <th>Email</th>
      <th>Role</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
      // Get the list of agents passed as a request attribute
      List<Account> agents = (List<Account>) request.getAttribute("agents");
      if (agents != null && !agents.isEmpty()) {
        for (Account agent : agents) {
    %>
    <tr>
      <td><%= agent.getUsername() %></td>
      <td><%= agent.getEmail() %></td>
      <td><%= agent.getRole() %></td>
      <td>
        <div class="d-flex">
          <!-- Edit button -->
          <a href="/Proj_war_exploded/updateAgent?username=<%= agent.getUsername() %>"
             class="btn btn-warning btn-sm me-2">Edit</a>
          <!-- Delete button -->
          <a href="/Proj_war_exploded/deleteAgent?username=<%= agent.getUsername() %>"
             class="btn btn-danger btn-sm"
             onclick="return confirm('Are you sure you want to delete this agent?');">Delete</a>
        </div>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="4" class="text-center">No agents found</td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
