package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "shipment_date", nullable = false)
    private LocalDate shipmentDate;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "expected_delivery")
    private LocalDate expectedDelivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private ShipmentCompany company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private SaleOrder sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private PurchaseOrder purchase;

}