package model;

public class User {
    private String nationalId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    // 1. Empty constructor (required by JDBC and some frameworks)
    public User() {}

    // 2. Full constructor (matches DAO requirements exactly)
    public User(String nationalId, String username, String email, 
               String password, String phoneNumber) {
        this.nationalId = nationalId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // 3. Additional constructors (won't affect DAO)
    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /* Original Getters and Setters - UNCHANGED */
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}