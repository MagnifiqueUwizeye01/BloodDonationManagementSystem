package dao;

import model.Donation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationDao {
    
    public int saveDonation(Donation donation) throws SQLException {
        String sql = "INSERT INTO donation (donor_id, donation_date, volume_ml, remarks) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pst.setInt(1, donation.getDonorId());
            pst.setString(2, donation.getDonationDate());
            pst.setInt(3, donation.getQuantity());
            pst.setString(4, donation.getRemarks());
            
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating donation failed, no rows affected.");
            }
            
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return affectedRows;
        }
    }

    public List<Donation> getAllDonations() throws SQLException {
        List<Donation> donations = new ArrayList<>();
        String sql = "SELECT * FROM donation ORDER BY donation_date DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                donations.add(new Donation(
                    rs.getInt("donation_id"),
                    rs.getInt("donor_id"),
                    rs.getString("donation_date"),
                    rs.getInt("volume_ml"),
                    rs.getString("remarks")
                ));
            }
        }
        return donations;
    }

    public List<Donation> getDonationsByDonor(int donorId) throws SQLException {
        List<Donation> donations = new ArrayList<>();
        String sql = "SELECT * FROM donation WHERE donor_id = ? ORDER BY donation_date DESC";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, donorId);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                donations.add(new Donation(
                    rs.getInt("donation_id"),
                    rs.getInt("donor_id"),
                    rs.getString("donation_date"),
                    rs.getInt("volume_ml"),
                    rs.getString("remarks")
                ));
            }
        }
        return donations;
    }

    public boolean deleteDonation(int donationId) throws SQLException {
        String sql = "DELETE FROM donation WHERE donation_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, donationId);
            return pst.executeUpdate() > 0;
        }
    }

    public boolean donorHasDonations(int donorId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM donation WHERE donor_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, donorId);
            ResultSet rs = pst.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // NEW METHOD: Update existing donation
    public boolean updateDonation(Donation donation) throws SQLException {
        String sql = "UPDATE donation SET donor_id = ?, donation_date = ?, volume_ml = ?, remarks = ? " +
                     "WHERE donation_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, donation.getDonorId());
            pst.setString(2, donation.getDonationDate());
            pst.setInt(3, donation.getQuantity());
            pst.setString(4, donation.getRemarks());
            pst.setInt(5, donation.getDonationId());
            
            return pst.executeUpdate() > 0;
        }
    }
}