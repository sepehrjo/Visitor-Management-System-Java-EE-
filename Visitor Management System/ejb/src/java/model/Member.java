package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class Member implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    // Password stored as a String (no restrictions now)
    @Column(nullable = false)
    private String password;
    
    // Role: "resident", "security", or "staff"
    @Column(nullable = false)
    private String role;
    
    private String gender;
    
    // Phone stored as numeric (long)
    private long phone;
    
    private String ic;
    private String email;
    private String address;
    
    public Member() {}

    public Member(String name, String password, String role, String gender, long phone, String ic, String email, String address) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.phone = phone;
        this.ic = ic;
        this.email = email;
        this.address = address;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public long getPhone() { return phone; }
    public void setPhone(long phone) { this.phone = phone; }
    public String getIc() { return ic; }
    public void setIc(String ic) { this.ic = ic; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
