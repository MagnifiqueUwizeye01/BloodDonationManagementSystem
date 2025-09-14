package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "donation")
public class Donation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private int donationId;

    // ✅ Many-to-One relationship to Donor 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Column(name = "donation_date")
    private String donationDate;

    @Column(name = "quantity") // Wrapper type avoids null error
    private Integer quantity;

    @Column(name = "remarks")
    private String remarks;

    public Donation() {}

    public Donation(int donationId, Donor donor, String donationDate, int quantity, String remarks) {
        this.donationId = donationId;
        this.donor = donor;
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.remarks = remarks;
    }

    public Donation(int donationId, int donorId, String donationDate, int quantity, String remarks) {
        this.donationId = donationId;
        this.donor = new Donor();
        this.donor.setDonorId(donorId); // Safe lightweight assignment
        this.donationDate = donationDate;
        this.quantity = quantity;
        this.remarks = remarks;
    }

    public int getDonationId() { return donationId; }
    public void setDonationId(int donationId) { this.donationId = donationId; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public String getDonationDate() { return donationDate; }
    public void setDonationDate(String donationDate) { this.donationDate = donationDate; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    // ✅ Still useful for table display or export
    public int getDonorId() {
        return (donor != null) ? donor.getDonorId() : 0;
    }
}
