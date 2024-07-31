package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "warehouse")
    private Set<Inventory> inventories = new LinkedHashSet<>();

}