package model;

import java.io.Serializable;

public class BloodInventory implements Serializable {

    private String bloodType;
    private int totalDonated;
    private int totalUsed;
    private int stock;

    public BloodInventory() {}

    // the constructor used by HQL
    public BloodInventory(String bloodType, Long totalDonated, Long totalUsed, Long stock) {
        this.bloodType = bloodType;
        this.totalDonated = totalDonated != null ? totalDonated.intValue() : 0;
        this.totalUsed = totalUsed != null ? totalUsed.intValue() : 0;
        this.stock = stock != null ? stock.intValue() : 0;
    }

    // Getters & Setters
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getTotalDonated() {
        return totalDonated;
    }

    public void setTotalDonated(int totalDonated) {
        this.totalDonated = totalDonated;
    }

    public int getTotalUsed() {
        return totalUsed;
    }

    public void setTotalUsed(int totalUsed) {
        this.totalUsed = totalUsed;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
