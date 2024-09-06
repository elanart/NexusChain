package com.nxc.pojo;

import com.nxc.enums.CriteriaEnum;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_rating")
public class SupplierRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CriteriaEnum criterion;

    @Column(precision = 3, scale = 2)
    private Double rating;

    @Column(length = 300)
    private String comment;

    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Column(name = "updated_date", insertable = false)
    private Date updatedDate;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Supplier supplier;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
