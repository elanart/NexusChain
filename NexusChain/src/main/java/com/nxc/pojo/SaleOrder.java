package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sale_order")
public class SaleOrder {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "order_date")
    private Instant orderDate;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id")
    private TaxRate tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToOne(mappedBy = "saleOrder")
    private SaleOrderDetail saleOrderDetail;

    @OneToMany(mappedBy = "sale")
    private Set<Shipment> shipments = new LinkedHashSet<>();

}