/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuann
 */
@Entity
@Table(name = "warehouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w"),
    @NamedQuery(name = "Warehouse.findByActive", query = "SELECT w FROM Warehouse w WHERE w.active = :active"),
    @NamedQuery(name = "Warehouse.findByCapacity", query = "SELECT w FROM Warehouse w WHERE w.capacity = :capacity"),
    @NamedQuery(name = "Warehouse.findByCost", query = "SELECT w FROM Warehouse w WHERE w.cost = :cost"),
    @NamedQuery(name = "Warehouse.findByCreatedDate", query = "SELECT w FROM Warehouse w WHERE w.createdDate = :createdDate"),
    @NamedQuery(name = "Warehouse.findById", query = "SELECT w FROM Warehouse w WHERE w.id = :id"),
    @NamedQuery(name = "Warehouse.findByUpdatedDate", query = "SELECT w FROM Warehouse w WHERE w.updatedDate = :updatedDate"),
    @NamedQuery(name = "Warehouse.findByLocation", query = "SELECT w FROM Warehouse w WHERE w.location = :location")})
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "capacity")
    private Integer capacity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Size(max = 255)
    @Column(name = "location")
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
    private Set<Shipment> shipmentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
    private Set<Inventory> inventorySet;
    @OneToMany(mappedBy = "warehouseId")
    private Set<Pricing> pricingSet;

    public Warehouse() {
    }

    public Warehouse(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Set<Shipment> getShipmentSet() {
        return shipmentSet;
    }

    public void setShipmentSet(Set<Shipment> shipmentSet) {
        this.shipmentSet = shipmentSet;
    }

    @XmlTransient
    public Set<Inventory> getInventorySet() {
        return inventorySet;
    }

    public void setInventorySet(Set<Inventory> inventorySet) {
        this.inventorySet = inventorySet;
    }

    @XmlTransient
    public Set<Pricing> getPricingSet() {
        return pricingSet;
    }

    public void setPricingSet(Set<Pricing> pricingSet) {
        this.pricingSet = pricingSet;
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
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.Warehouse[ id=" + id + " ]";
    }
    
}
