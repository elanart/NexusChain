package com.nxc.pojo;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private Integer capacity;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @Builder.Default
    @Column(name = "active")
    private Boolean isActive = true;

    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Column(name = "updated_date", insertable = false)
    private Date updatedDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "warehouse")
    private Set<Inventory> inventories;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "warehouse")
    private Set<Pricing> pricings;

    @OneToMany(mappedBy = "warehouse")
    private Set<Shipment> shipments;

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }
}
