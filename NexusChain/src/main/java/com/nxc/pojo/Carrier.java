package com.nxc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "carrier")
public class Carrier implements Serializable {
    @Id
    private Long id;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "cooperation_terms", length = 300)
    private String cooperationTerms;

    @JsonIgnore
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId
    @OneToOne(optional = false)
    private User user;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "carrier")
    private Set<Shipment> shipments;
}
