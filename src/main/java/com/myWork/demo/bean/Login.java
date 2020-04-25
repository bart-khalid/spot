/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.accessibility.internal.resources.accessibility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author lenovo
 */
@Entity
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private double nbrSpot;
    private double nbrFidelite;
    
    
    @OneToMany(mappedBy = "login")
    private List<Spot> mySpots;
    
    
    @OneToMany(mappedBy = "userLovedSpot")
    private List<Spot> myLikedSpots;

    public Login() {
    }

    public List<Spot> getMyLikedSpots() {
        return myLikedSpots;
    }

    public void setMyLikedSpots(List<Spot> myLikedSpots) {
        this.myLikedSpots = myLikedSpots;
    }
    

    
    public List<Spot> getMySpots() {
        return mySpots;
    }

    public void setMySpots(List<Spot> mySpots) {
        this.mySpots = mySpots;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNbrSpot() {
        return nbrSpot;
    }

    public void setNbrSpot(double nbrSpot) {
        this.nbrSpot = nbrSpot;
    }

    public double getNbrFidelite() {
        return nbrFidelite;
    }

    public void setNbrFidelite(double nbrFidelite) {
        this.nbrFidelite = nbrFidelite;
    }
    
    
    

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
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myWork.bean.Login[ id=" + id + " ]";
    }
    
}
