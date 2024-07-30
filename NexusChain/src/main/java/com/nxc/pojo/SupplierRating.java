/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "supplier_rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierRating.findAll", query = "SELECT s FROM SupplierRating s"),
    @NamedQuery(name = "SupplierRating.findByRatingId", query = "SELECT s FROM SupplierRating s WHERE s.ratingId = :ratingId"),
    @NamedQuery(name = "SupplierRating.findByReviewDate", query = "SELECT s FROM SupplierRating s WHERE s.reviewDate = :reviewDate"),
    @NamedQuery(name = "SupplierRating.findByCriteria", query = "SELECT s FROM SupplierRating s WHERE s.criteria = :criteria"),
    @NamedQuery(name = "SupplierRating.findByRating", query = "SELECT s FROM SupplierRating s WHERE s.rating = :rating")})
public class SupplierRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "rating_id")
    private String ratingId;
    @Column(name = "review_date")
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    @Size(max = 100)
    @Column(name = "criteria")
    private String criteria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rating")
    private BigDecimal rating;
    @Lob
    @Size(max = 65535)
    @Column(name = "comments")
    private String comments;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplierId;

    public SupplierRating() {
    }

    public SupplierRating(String ratingId) {
        this.ratingId = ratingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingId != null ? ratingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierRating)) {
            return false;
        }
        SupplierRating other = (SupplierRating) object;
        return (this.ratingId != null || other.ratingId == null) && (this.ratingId == null || this.ratingId.equals(other.ratingId));
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.SupplierRating[ ratingId=" + ratingId + " ]";
    }
    
}
