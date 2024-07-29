/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Pricing.findByPrice", query = "SELECT p FROM Pricing p WHERE p.price = :price"),
    @NamedQuery(name = "Pricing.findByEffectiveDate", query = "SELECT p FROM Pricing p WHERE p.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "Pricing.findByProductId", query = "SELECT p FROM Pricing p WHERE p.pricingPK.productId = :productId"),
    @NamedQuery(name = "Pricing.findBySupplierId", query = "SELECT p FROM Pricing p WHERE p.pricingPK.supplierId = :supplierId")})
public class Pricing implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PricingPK pricingPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Supplier supplier;

    public Pricing() {
    }

    public Pricing(PricingPK pricingPK) {
        this.pricingPK = pricingPK;
    }

    public Pricing(String productId, String supplierId) {
        this.pricingPK = new PricingPK(productId, supplierId);
    }

    public PricingPK getPricingPK() {
        return pricingPK;
    }

    public void setPricingPK(PricingPK pricingPK) {
        this.pricingPK = pricingPK;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricingPK != null ? pricingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pricing)) {
            return false;
        }
        Pricing other = (Pricing) object;
        if ((this.pricingPK == null && other.pricingPK != null) || (this.pricingPK != null && !this.pricingPK.equals(other.pricingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.Pricing[ pricingPK=" + pricingPK + " ]";
    }
    
}
