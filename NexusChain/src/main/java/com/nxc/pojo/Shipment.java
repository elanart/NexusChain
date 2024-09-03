package com.nxc.pojo;

import com.nxc.enums.OrderStatusEnum;
import javax.persistence.*;

import com.nxc.enums.ShippingStatusEnum;
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
@Table(name = "shipment")
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipment_date", nullable = false)
    private Date shipmentDate;

    @Enumerated(EnumType.STRING)
    private ShippingStatusEnum status;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "expected_delivery")
    private Date expectedDelivery;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "destination")
    private String destination;

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
