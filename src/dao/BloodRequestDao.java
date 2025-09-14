package dao;

import model.BloodRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodRequestDao {
    
    public int saveRequest(BloodRequest request) throws SQLException {
        String sql = "INSERT INTO blood_request (patient_name, blood_type_needed, quantity_needed, request_date, status) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pst.setString(1, request.getPatientName());
            pst.setString(2, request.getBloodType());
            pst.setInt(3, request.getQuantity());
            pst.setString(4, request.getRequestDate());
            pst.setString(5, request.getStatus());
            
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating request failed, no rows affected.");
            }
            
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Creating request failed, no ID obtained.");
                }
            }
        }
    }

    public boolean updateRequest(BloodRequest request) throws SQLException {
        String sql = "UPDATE blood_request SET patient_name = ?, blood_type_needed = ?, quantity_needed = ?, " +
                     "request_date = ?, status = ? WHERE request_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, request.getPatientName());
            pst.setString(2, request.getBloodType());
            pst.setInt(3, request.getQuantity());
            pst.setString(4, request.getRequestDate());
            pst.setString(5, request.getStatus());
            pst.setInt(6, request.getId());
            
            return pst.executeUpdate() > 0;
        }
    }

    public List<BloodRequest> getAllRequests() throws SQLException {
        List<BloodRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM blood_request ORDER BY request_date DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                requests.add(new BloodRequest(
                    rs.getInt("request_id"),
                    rs.getString("patient_name"),
                    rs.getString("blood_type_needed"),
                    rs.getInt("quantity_needed"),
                    rs.getString("request_date"),
                    rs.getString("status")
                ));
            }
        }
        return requests;
    }

    public BloodRequest getRequestById(int requestId) throws SQLException {
        String sql = "SELECT * FROM blood_request WHERE request_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, requestId);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new BloodRequest(
                        rs.getInt("request_id"),
                        rs.getString("patient_name"),
                        rs.getString("blood_type_needed"),
                        rs.getInt("quantity_needed"),
                        rs.getString("request_date"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    public boolean deleteRequest(int requestId) throws SQLException {
        String sql = "DELETE FROM blood_request WHERE request_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, requestId);
            return pst.executeUpdate() > 0;
        }
    }

    public List<BloodRequest> getRequestsByStatus(String status) throws SQLException {
        List<BloodRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM blood_request WHERE status = ? ORDER BY request_date DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, status);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    requests.add(new BloodRequest(
                        rs.getInt("request_id"),
                        rs.getString("patient_name"),
                        rs.getString("blood_type_needed"),
                        rs.getInt("quantity_needed"),
                        rs.getString("request_date"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return requests;
    }

    public List<BloodRequest> getRequestsByBloodType(String bloodType) throws SQLException {
        List<BloodRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM blood_request WHERE blood_type_needed = ? ORDER BY request_date DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, bloodType);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    requests.add(new BloodRequest(
                        rs.getInt("request_id"),
                        rs.getString("patient_name"),
                        rs.getString("blood_type_needed"),
                        rs.getInt("quantity_needed"),
                        rs.getString("request_date"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return requests;
    }
}