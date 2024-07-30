/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuann
 */
@Setter
@Getter
@Entity
@Table(name = "shipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shipment.findAll", query = "SELECT s FROM Shipment s"),
    @NamedQuery(name = "Shipment.findById", query = "SELECT s FROM Shipment s WHERE s.id = :id"),
    @NamedQuery(name = "Shipment.findByShipmentDate", query = "SELECT s FROM Shipment s WHERE s.shipmentDate = :shipmentDate"),
    @NamedQuery(name = "Shipment.findByStatus", query = "SELECT s FROM Shipment s WHERE s.status = :status"),
    @NamedQuery(name = "Shipment.findByTrackingNumber", query = "SELECT s FROM Shipment s WHERE s.trackingNumber = :trackingNumber"),
    @NamedQuery(name = "Shipment.findByExpectedDelivery", query = "SELECT s FROM Shipment s WHERE s.expectedDelivery = :expectedDelivery")})
public class Shipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipment_date")
    @Temporal(TemporalType.DATE)
    private Date shipmentDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @Size(max = 100)
    @Column(name = "tracking_number")
    private String trackingNumber;
    @Column(name = "expected_delivery")
    @Temporal(TemporalType.DATE)
    private Date expectedDelivery;
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    @ManyToOne
    private PurchaseOrder purchaseId;
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    @ManyToOne
    private SaleOrder saleId;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private ShipmentCompany companyId;

    public Shipment() {
    }

    public Shipment(String id) {
        this.id = id;
    }

    public Shipment(String id, Date shipmentDate, String status) {
        this.id = id;
        this.shipmentDate = shipmentDate;
        this.status = status;
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
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.Shipment[ id=" + id + " ]";
    }
    
}
