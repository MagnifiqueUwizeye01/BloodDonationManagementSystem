package dao;

import model.Donor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorDao {
    
    public int saveDonor(Donor donor) throws SQLException {
        String sql = "INSERT INTO donor (names, blood_type, phone, email) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pst.setString(1, donor.getNames());
            pst.setString(2, donor.getBloodType());
            pst.setString(3, donor.getPhone());
            pst.setString(4, donor.getEmail());
            
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating donor failed, no rows affected.");
            }
            
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Creating donor failed, no ID obtained.");
                }
            }
        }
    }

    public int updateDonor(Donor donor) throws SQLException {
        String sql = "UPDATE donor SET names = ?, blood_type = ?, phone = ?, email = ? WHERE donor_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, donor.getNames());
            pst.setString(2, donor.getBloodType());
            pst.setString(3, donor.getPhone());
            pst.setString(4, donor.getEmail());
            pst.setInt(5, donor.getDonorId());
            
            return pst.executeUpdate();
        }
    }
public boolean donorExists(String name, String phone) throws SQLException {
    String sql = "SELECT 1 FROM donor WHERE LOWER(TRIM(names)) = ? AND REPLACE(phone, ' ', '') = ? LIMIT 1";
    
    try (Connection con = DatabaseConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setString(1, name.trim().toLowerCase());
        pst.setString(2, phone.replaceAll("\\s", ""));
        
        try (ResultSet rs = pst.executeQuery()) {
            return rs.next(); // True if exists
        }
    }
}


    public List<Donor> getAllDonors() throws SQLException {
        List<Donor> donors = new ArrayList<>();
        String sql = "SELECT * FROM donor ORDER BY names";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                donors.add(new Donor(
                    rs.getInt("donor_id"),
                    rs.getString("names"),
                    rs.getString("blood_type"),
                    rs.getString("phone"),
                    rs.getString("email")
                ));
            }
        }
        return donors;
    }

    public List<String> getAllDonorsFormatted() throws SQLException {
        List<String> donors = new ArrayList<>();
        String sql = "SELECT donor_id, names, blood_type FROM donor ORDER BY names";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String name = rs.getString("names") != null ? rs.getString("names").trim() : "Unknown";
                String bloodType = rs.getString("blood_type") != null ? rs.getString("blood_type").trim() : "Unknown";
                donors.add(String.format("%s (ID: %d, Blood: %s)", name, rs.getInt("donor_id"), bloodType));
            }
        }
        return donors;
    }

    public int deleteDonor(int donorId) throws SQLException {
        String sql = "DELETE FROM donor WHERE donor_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, donorId);
            return pst.executeUpdate();
        }
    }

    public boolean hasDonations(int donorId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM donation WHERE donor_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, donorId);
            ResultSet rs = pst.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public Donor getDonorById(int donorId) throws SQLException {
        String sql = "SELECT * FROM donor WHERE donor_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, donorId);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Donor(
                        rs.getInt("donor_id"),
                        rs.getString("names"),
                        rs.getString("blood_type"),
                        rs.getString("phone"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    public List<Donor> getDonorsByBloodType(String bloodType) throws SQLException {
        List<Donor> donors = new ArrayList<>();
        String sql = "SELECT * FROM donor WHERE blood_type = ? ORDER BY names";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, bloodType);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    donors.add(new Donor(
                        rs.getInt("donor_id"),
                        rs.getString("names"),
                        rs.getString("blood_type"),
                        rs.getString("phone"),
                        rs.getString("email")
                    ));
                }
            }
        }
        return donors;
    }

    // NEW METHOD: Check if donor exists
    public boolean donorExists(int donorId) throws SQLException {
        String sql = "SELECT 1 FROM donor WHERE donor_id = ? LIMIT 1";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, donorId);
            
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); // Returns true if donor exists
            }
        }
    }
}