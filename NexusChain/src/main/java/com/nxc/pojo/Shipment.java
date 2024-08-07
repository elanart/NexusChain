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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuann
 */
@Entity
@Table(name = "shipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shipment.findAll", query = "SELECT s FROM Shipment s"),
    @NamedQuery(name = "Shipment.findByCost", query = "SELECT s FROM Shipment s WHERE s.cost = :cost"),
    @NamedQuery(name = "Shipment.findByExpectedDelivery", query = "SELECT s FROM Shipment s WHERE s.expectedDelivery = :expectedDelivery"),
    @NamedQuery(name = "Shipment.findById", query = "SELECT s FROM Shipment s WHERE s.id = :id"),
    @NamedQuery(name = "Shipment.findByShipmentDate", query = "SELECT s FROM Shipment s WHERE s.shipmentDate = :shipmentDate"),
    @NamedQuery(name = "Shipment.findByTrackingNumber", query = "SELECT s FROM Shipment s WHERE s.trackingNumber = :trackingNumber"),
    @NamedQuery(name = "Shipment.findByStatus", query = "SELECT s FROM Shipment s WHERE s.status = :status")})
public class Shipment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "expected_delivery")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedDelivery;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDate;
    @Size(max = 100)
    @Column(name = "tracking_number")
    private String trackingNumber;
    @Size(max = 9)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "carrier_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Carrier carrierId;
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Warehouse warehouseId;
    @OneToOne(mappedBy = "shipmentId")
    private Invoice invoice;
    @OneToMany(mappedBy = "shipmentId")
    private Set<Pricing> pricingSet;

    public Shipment() {
    }

    public Shipment(Long id) {
        this.id = id;
    }

    public Shipment(Long id, Date shipmentDate) {
        this.id = id;
        this.shipmentDate = shipmentDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(Date expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Carrier getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Carrier carrierId) {
        this.carrierId = carrierId;
    }

    public Warehouse getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Warehouse warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
        if (!(object instanceof Shipment)) {
            return false;
        }
        Shipment other = (Shipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.Shipment[ id=" + id + " ]";
    }
    
}
