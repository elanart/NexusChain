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
@Table(name = "tax_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaxRate.findAll", query = "SELECT t FROM TaxRate t"),
    @NamedQuery(name = "TaxRate.findById", query = "SELECT t FROM TaxRate t WHERE t.id = :id"),
    @NamedQuery(name = "TaxRate.findByRegion", query = "SELECT t FROM TaxRate t WHERE t.region = :region"),
    @NamedQuery(name = "TaxRate.findByRate", query = "SELECT t FROM TaxRate t WHERE t.rate = :rate")})
public class TaxRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Size(max = 100)
    @Column(name = "region")
    private String region;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rate")
    private BigDecimal rate;
    @OneToMany(mappedBy = "taxId")
    private Set<PurchaseOrder> purchaseOrderSet;
    @OneToMany(mappedBy = "taxId")
    private Set<SaleOrder> saleOrderSet;
    @OneToMany(mappedBy = "taxId")
    private Set<Invoice> invoiceSet;

    public TaxRate() {
    }

    public TaxRate(String id) {
        this.id = id;
    }

    @XmlTransient
    public Set<PurchaseOrder> getPurchaseOrderSet() {
        return purchaseOrderSet;
    }

    @XmlTransient
    public Set<SaleOrder> getSaleOrderSet() {
        return saleOrderSet;
    }

    @XmlTransient
    public Set<Invoice> getInvoiceSet() {
        return invoiceSet;
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
        if (!(object instanceof TaxRate)) {
            return false;
        }
        TaxRate other = (TaxRate) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.TaxRate[ id=" + id + " ]";
    }
    
}
