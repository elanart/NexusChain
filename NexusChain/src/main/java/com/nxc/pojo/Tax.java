package com.nxc.pojo;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tax")
public class Tax implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String region;

    @Column(precision = 5, scale = 2)
    private BigDecimal rate;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "tax")
    private Set<Invoice> invoices;
}
