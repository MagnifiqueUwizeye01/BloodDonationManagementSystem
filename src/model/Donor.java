package model;

public class Donor {
    private int donorId;
    private String names;
    private String bloodType;
    private String phone;
    private String email;

    // Constructor
    public Donor(int donorId, String names, String bloodType, String phone, String email) {
        this.donorId = donorId;
        this.names = names;
        this.bloodType = bloodType;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
