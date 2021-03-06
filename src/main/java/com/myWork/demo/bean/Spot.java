/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myWork.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author lenovo
 */
@Entity
public class Spot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @Size(min = 3, max = 1000)
    private String spotText;
    private String username;
    @JsonFormat(pattern = "MM-dd-YY")
    private Date date;
    private double nbrLike;
    private double nbrComments;
    private String email;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Login login;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "spot")
    private List<SpotDetails> spots;

    
    @OneToMany(mappedBy = "spot")
    private List<Comment> comments;

    public double getNbrComments() {
        return nbrComments;
    }

    public void setNbrComments(double nbrComments) {
        this.nbrComments = nbrComments;
    }

    public List<SpotDetails> getSpots() {
        return spots;
    }

    public void setSpots(List<SpotDetails> spots) {
        this.spots = spots;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    

    public double getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(double nbrLike) {
        this.nbrLike = nbrLike;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getSpotText() {
        return spotText;
    }

    public void setSpotText(String spotText) {
        this.spotText = spotText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (!(object instanceof Spot)) {
            return false;
        }
        Spot other = (Spot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myWork.demo.bean.Spot[ id=" + id + " ]";
    }

}
