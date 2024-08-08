package com.nxc.pojo;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pricing")
public class Pricing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "id")
    private User supplier;

    @ManyToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Column(name = "updated_date", insertable = false)
    private Date updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }
}
