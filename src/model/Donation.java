package model;

public class Donation {
    private int donationId;
    private int donorId;
    private String donationDate;
    private int quantity;  // This matches volume_ml in database
    private String remarks;

    public Donation(int donationId, int donorId, String donationDate, int quantity, String remarks) {
        this.donationId = donationId;
        this.donorId = donorId;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.remarks = remarks;
    }

    // Getters and setters
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}