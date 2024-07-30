/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tuann
 */
@Setter
@Getter
@Embeddable
public class PricingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "supplier_id")
    private String supplierId;

    public PricingPK() {
    }

    public PricingPK(String productId, String supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PricingPK)) {
            return false;
        }
        PricingPK other = (PricingPK) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return (this.supplierId != null || other.supplierId == null) && (this.supplierId == null || this.supplierId.equals(other.supplierId));
    }

    @Override
    public String toString() {
        return "com.nxc.pojo.PricingPK[ productId=" + productId + ", supplierId=" + supplierId + " ]";
    }
    
}
