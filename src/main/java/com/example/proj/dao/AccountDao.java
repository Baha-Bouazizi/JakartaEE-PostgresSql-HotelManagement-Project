package com.example.proj.dao;

import com.example.proj.Models.Account;
import com.example.proj.utils.DatabaseConnection;

import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    // Method to validate login credentials
    public boolean validateLogin(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM Account WHERE username = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPasswordHash = rs.getString("password");
                // Check if the entered password matches the stored hash
                return storedPasswordHash.equals(Account.hashPassword(password));
            }
        }
        return false;
    }

    // Method to create a new account (with hashed password)
    public void createAccount(Account account) throws SQLException, NoSuchAlgorithmException {
        String hashedPassword = Account.hashPassword(account.getPassword());  // Hash the password
        String query = "INSERT INTO Account (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, account.getUsername());
            stmt.setString(2, hashedPassword);  // Store the hashed password
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getRole());  // Store the role
            stmt.executeUpdate();
        }
    }

    // Method to create the default admin if not exists
    public void createDefaultAdmin() throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT COUNT(*) FROM Account WHERE username = 'admin'";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) {
                // Default admin doesn't exist, create one
                Account admin = new Account("admin", "admin123", "admin.admin@gmail.com", "admin");
                createAccount(admin);
            }
        }
    }

    // Method to get all agents
    public List<Account> getAgents() throws SQLException {
        List<Account> agents = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE role = 'agent'";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Account agent = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
                agents.add(agent);
            }
        }
        return agents;
    }

    // Optional: Validate if the role is valid (admin, agent, or client)
    public boolean validateRole(String role) {
        return role.equals("admin") || role.equals("agent") || role.equals("client");
    }
    public List<String> validateLoginAndGetRoleAndId(String username, String password) throws SQLException, NoSuchAlgorithmException {
        List<String> x = new ArrayList<>();
        String query = "SELECT password, role, id FROM Account WHERE username = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPasswordHash = rs.getString("password");
                    // Use a safe password comparison
                    if (Account.hashPassword(password).equals(storedPasswordHash)) {
                        x.add(rs.getString("role"));
                        x.add(rs.getString("id"));
                        return x;  // Return the role and id if password matches
                    }
                }
            }
        }
        return null;  // Return null if the credentials are invalid
    }

    public Account getAgentByUsername(String username) throws SQLException {
        String query = "SELECT * FROM Account WHERE username = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
            }
        }
        return null;
    }
    public void updateAgent(String username, String email) throws SQLException {
        String query = "UPDATE Account SET email = ? WHERE username = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
    }
    public void deleteAgent(String username) throws SQLException {
        String query = "DELETE FROM Account WHERE username = ?";
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

}
