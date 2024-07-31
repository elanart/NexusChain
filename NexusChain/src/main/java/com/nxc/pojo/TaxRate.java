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
@Table(name = "tax_rate")
public class TaxRate {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "region", length = 100)
    private String region;

    @Column(name = "rate", precision = 5, scale = 2)
    private BigDecimal rate;

    @OneToMany(mappedBy = "tax")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tax")
    private Set<PurchaseOrder> purchaseOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "tax")
    private Set<SaleOrder> saleOrders = new LinkedHashSet<>();

}