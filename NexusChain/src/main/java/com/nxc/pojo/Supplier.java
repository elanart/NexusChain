package com.nxc.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private UUID id;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 50, message = "{supplier.name.errMsg}")
    private String name;

    @Column(name = "address")
    @Size(min = 1, max = 50, message = "{supplier.address.errMsg}")
    private String address;

    @Column(name = "phone", length = 50)
    @Size(min = 1, max = 50, message = "{supplier.phone.errMsg}")
    private String phone;

    @Column(name = "contact_info", length = 100)
    @Size(min = 1, max = 50, message = "{supplier.contactInfo.errMsg}")
    private String contactInfo;

    @Column(name = "payment_terms", length = 100)
    @Size(min = 1, max = 50, message = "{supplier.paymentTerms.errMsg}")
    private String paymentTerms;

    @Column(name = "rating", precision = 3, scale = 2)
//    @Min( value = "1", message = "{supplier.rating.errMsg}");
//    @Max(value = 5, message = "{supplier.rating.errMsg}");
    private BigDecimal rating;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "supplier")
    private Set<Pricing> pricings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<PurchaseOrder> purchaseOrders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierProduct> supplierProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierRating> supplierRatings = new LinkedHashSet<>();
    
    public Supplier() {
        this.id = UUID.randomUUID();
    }
}
