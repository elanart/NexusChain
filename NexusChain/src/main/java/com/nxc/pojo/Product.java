package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<Inventory> inventories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Pricing> pricings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<PurchaseOrderDetail> purchaseOrderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<SaleOrderDetail> saleOrderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<SupplierProduct> supplierProducts = new LinkedHashSet<>();

}