package com.nxc.pojo;

import com.nxc.enums.OrderStatusEnum;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipment_date", nullable = false)
    private LocalDateTime shipmentDate;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "expected_delivery")
    private LocalDateTime expectedDelivery;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "carrier_id", referencedColumnName = "id", nullable = false)
    private Carrier carrier;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "shipment")
    private Invoice invoice;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "shipment")
    private Set<Pricing> pricings;
}
