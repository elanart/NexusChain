package com.nxc.pojo;

import com.nxc.enums.CriteriaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {
    @Id
    private Long id;

    @Column(name = "payment_terms", length = 300)
    private String paymentTerms;

    @Column(name = "review_date", updatable = false)
    private LocalDateTime reviewDate;

    @Enumerated(EnumType.STRING)
    private CriteriaEnum criterion;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(length = 300)
    private String comment;

    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId
    @OneToOne(optional = false)
    private User user;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "supplier")
    private Set<SupplierProduct> supplierProducts;
}
