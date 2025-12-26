/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sepehrjokanian
 */
@Entity
public class SecurityVisitorRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    //Additional
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String password;
    
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

    public SecurityVisitorRecord() {}
    
    
    public SecurityVisitorRecord(String name, String password, String gender, String phone, String ic, String email, String address) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.ic = ic;
        this.email = email;
        this.address = address;
    }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getIc() { return ic; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    
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
        if (!(object instanceof SecurityVisitorRecord)) {
            return false;
        }
        SecurityVisitorRecord other = (SecurityVisitorRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SecurityVisitorRecord[ id=" + id + " ]";
    }
    
}
