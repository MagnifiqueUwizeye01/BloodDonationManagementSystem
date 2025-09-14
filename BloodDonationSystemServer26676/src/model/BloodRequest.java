package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blood_requests")
public class BloodRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int id;

    @Column(name = "patient_name", nullable = false, length = 100)
    private String patientName;

    @Column(name = "blood_type", nullable = false, length = 5)
    private String bloodType;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "request_date", nullable = false)
    private String requestDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    // Constructors
    public BloodRequest() {}

    public BloodRequest(int id, String patientName, String bloodType, int quantity, String requestDate, String status) {
        this.id = id;
        this.patientName = patientName;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getRequestDate() { return requestDate; }
    public void setRequestDate(String requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
