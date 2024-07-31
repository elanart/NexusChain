package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
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

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "customer")
    private Set<SaleOrder> saleOrders = new LinkedHashSet<>();

}