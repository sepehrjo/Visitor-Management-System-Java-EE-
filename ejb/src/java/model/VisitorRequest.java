/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sepehrjokanian
 */
@Entity
public class VisitorRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //additional
     @Column(nullable = false)
    private String visitorName;
    
    @Column(nullable = false)
    private String visitorPassword;
    
    @Column(nullable = false)
    private String gender;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private String ic;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String reason;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date visitDate;
    
    @ManyToOne
    @JoinColumn(name="resident_id", nullable=false)
    private Member resident;  // Reference to the resident who requested the visit

    @Column(nullable = false)
    private String status; // Submitted, Cancelled, Closed
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    
     public VisitorRequest() {}
     
      // Constructor to store visitor request details
    public VisitorRequest(String visitorName, String visitorPassword, String gender, String phone, String ic, String email, String address, Date visitDate, String reason, Member resident) {
        this.visitorName = visitorName;
        this.visitorPassword = visitorPassword;
        this.gender = gender;
        this.phone = phone;
        this.ic = ic;
        this.email = email;
        this.address = address;
        this.visitDate = visitDate;
        this.reason = reason;
        this.resident = resident;
        this.status = "Submitted";
        this.requestDate = new Date();
    }
     
    
    public String getVisitorName() { return visitorName; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }
    public String getVisitorPassword() { return visitorPassword; }
    public void setVisitorPassword(String visitorPassword) { this.visitorPassword = visitorPassword; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getIc() { return ic; }
    public void setIc(String ic) { this.ic = ic; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Date getVisitDate() { return visitDate; }
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Member getResident() { return resident; }
    public void setResident(Member resident) { this.resident = resident; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    
    
    //additional

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisitorRequest)) {
            return false;
        }
        VisitorRequest other = (VisitorRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VisitorRequest[ id=" + id + " ]";
    }
    
    
    
}
