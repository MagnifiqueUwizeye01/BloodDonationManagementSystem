package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/blood_donation_management_system_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Get connection method
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Test connection method
    public static void testConnection() {
        try (Connection con = getConnection()) {
            if (con != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while testing the connection: " + e.getMessage());
        }
    }

    // Main method to run the connection test
    public static void main(String[] args) {
        testConnection();  // Test the database connection
    }
}
