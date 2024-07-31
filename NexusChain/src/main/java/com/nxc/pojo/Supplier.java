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
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "contact_info", length = 100)
    private String contactInfo;

    @Column(name = "payment_terms", length = 100)
    private String paymentTerms;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "supplier")
    private Set<Pricing> pricings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<PurchaseOrder> purchaseOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierProduct> supplierProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierRating> supplierRatings = new LinkedHashSet<>();

}