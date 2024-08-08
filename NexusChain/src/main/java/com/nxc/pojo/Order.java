package com.nxc.pojo;

import com.nxc.enums.OrderTypeEnum;
import com.nxc.enums.ShippingStatusEnum;
import javax.persistence.*;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private ShippingStatusEnum status;

    @Enumerated(EnumType.STRING)
    private OrderTypeEnum type;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "order")
    private Set<Invoice> invoices;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "order")
    private OrderDetail orderDetail;
}
