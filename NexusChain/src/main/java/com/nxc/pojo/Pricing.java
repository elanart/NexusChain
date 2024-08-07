/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuann
 */
@Entity
@Table(name = "pricing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pricing.findAll", query = "SELECT p FROM Pricing p"),
    @NamedQuery(name = "Pricing.findByCreatedDate", query = "SELECT p FROM Pricing p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Pricing.findById", query = "SELECT p FROM Pricing p WHERE p.id = :id"),
    @NamedQuery(name = "Pricing.findByUpdatedDate", query = "SELECT p FROM Pricing p WHERE p.updatedDate = :updatedDate")})
public class Pricing implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    @ManyToOne
    private Shipment shipmentId;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User supplierId;
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @ManyToOne
    private Warehouse warehouseId;

    public Pricing() {
    }

    public Pricing(Long id) {
        this.id = id;
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

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Shipment getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Shipment shipmentId) {
        this.shipmentId = shipmentId;
    }

    public User getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(User supplierId) {
        this.supplierId = supplierId;
    }

    public Warehouse getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Warehouse warehouseId) {
        this.warehouseId = warehouseId;
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
        if (!(object instanceof Pricing)) {
            return false;
        }
        Pricing other = (Pricing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.Pricing[ id=" + id + " ]";
    }
    
}
