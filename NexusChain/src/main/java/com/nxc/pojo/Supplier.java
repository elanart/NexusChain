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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findById", query = "SELECT s FROM Supplier s WHERE s.id = :id"),
    @NamedQuery(name = "Supplier.findByName", query = "SELECT s FROM Supplier s WHERE s.name = :name"),
    @NamedQuery(name = "Supplier.findByAddress", query = "SELECT s FROM Supplier s WHERE s.address = :address"),
    @NamedQuery(name = "Supplier.findByPhone", query = "SELECT s FROM Supplier s WHERE s.phone = :phone"),
    @NamedQuery(name = "Supplier.findByContactInfo", query = "SELECT s FROM Supplier s WHERE s.contactInfo = :contactInfo"),
    @NamedQuery(name = "Supplier.findByPaymentTerms", query = "SELECT s FROM Supplier s WHERE s.paymentTerms = :paymentTerms"),
    @NamedQuery(name = "Supplier.findByRating", query = "SELECT s FROM Supplier s WHERE s.rating = :rating"),
    @NamedQuery(name = "Supplier.findByActive", query = "SELECT s FROM Supplier s WHERE s.active = :active")})
public class Supplier implements Serializable {

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
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "phone")
    private String phone;
    @Size(max = 100)
    @Column(name = "contact_info")
    private String contactInfo;
    @Size(max = 100)
    @Column(name = "payment_terms")
    private String paymentTerms;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private BigDecimal rating;
    @Column(name = "active")
    private Boolean active;
    @JoinTable(name = "supplier_product", joinColumns = {
        @JoinColumn(name = "supplier_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Product> productSet;
    @OneToMany(mappedBy = "supplierId")
    private Set<SupplierRating> supplierRatingSet;
    @OneToMany(mappedBy = "supplierId")
    private Set<PurchaseOrder> purchaseOrderSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private Set<Pricing> pricingSet;

    public Supplier() {
    }

    public Supplier(String id) {
        this.id = id;
    }

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @XmlTransient
    public Set<Product> getProductSet() {
        return productSet;
    }

    @XmlTransient
    public Set<SupplierRating> getSupplierRatingSet() {
        return supplierRatingSet;
    }

    @XmlTransient
    public Set<PurchaseOrder> getPurchaseOrderSet() {
        return purchaseOrderSet;
    }

    @XmlTransient
    public Set<Pricing> getPricingSet() {
        return pricingSet;
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
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.Supplier[ id=" + id + " ]";
    }
    
}
