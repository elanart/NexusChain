package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PricingId implements java.io.Serializable {
    private static final long serialVersionUID = 4925994280207012883L;
    @Column(name = "product_id", nullable = false, length = 50)
    private String productId;

    @Column(name = "supplier_id", nullable = false, length = 50)
    private String supplierId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PricingId entity = (PricingId) o;
        return Objects.equals(this.supplierId, entity.supplierId) &&
                Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, productId);
    }

}