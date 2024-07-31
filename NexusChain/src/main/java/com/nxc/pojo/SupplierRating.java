package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "supplier_rating")
public class SupplierRating {
    @Id
    @Column(name = "rating_id", nullable = false, length = 50)
    private String ratingId;

    @Column(name = "review_date")
    private LocalDate reviewDate;

    @Column(name = "criteria", length = 100)
    private String criteria;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Lob
    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}