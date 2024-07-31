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
@Table(name = "purchase_order")
public class PurchaseOrder {
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
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "purchase")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToOne(mappedBy = "purchaseOrder")
    private PurchaseOrderDetail purchaseOrderDetail;

    @OneToMany(mappedBy = "purchase")
    private Set<Shipment> shipments = new LinkedHashSet<>();

}