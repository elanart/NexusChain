/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuann
 */
@Getter
@Setter
@Entity
@Table(name = "shipment_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShipmentCompany.findAll", query = "SELECT s FROM ShipmentCompany s"),
    @NamedQuery(name = "ShipmentCompany.findById", query = "SELECT s FROM ShipmentCompany s WHERE s.id = :id"),
    @NamedQuery(name = "ShipmentCompany.findByCompanyName", query = "SELECT s FROM ShipmentCompany s WHERE s.companyName = :companyName"),
    @NamedQuery(name = "ShipmentCompany.findByContactInfo", query = "SELECT s FROM ShipmentCompany s WHERE s.contactInfo = :contactInfo"),
    @NamedQuery(name = "ShipmentCompany.findByActive", query = "SELECT s FROM ShipmentCompany s WHERE s.active = :active"),
    @NamedQuery(name = "ShipmentCompany.findByRating", query = "SELECT s FROM ShipmentCompany s WHERE s.rating = :rating")})
public class ShipmentCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "company_name")
    private String companyName;
    @Size(max = 255)
    @Column(name = "contact_info")
    private String contactInfo;
    @Column(name = "active")
    private Boolean active;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private BigDecimal rating;
    @OneToMany(mappedBy = "companyId")
    private Set<Shipment> shipmentSet;

    public ShipmentCompany() {
    }

    public ShipmentCompany(String id) {
        this.id = id;
    }

    public ShipmentCompany(String id, String companyName, BigDecimal rating) {
        this.id = id;
        this.companyName = companyName;
        this.rating = rating;
    }

    @XmlTransient
    public Set<Shipment> getShipmentSet() {
        return shipmentSet;
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
        if (!(object instanceof ShipmentCompany)) {
            return false;
        }
        ShipmentCompany other = (ShipmentCompany) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.ShipmentCompany[ id=" + id + " ]";
    }
    
}
