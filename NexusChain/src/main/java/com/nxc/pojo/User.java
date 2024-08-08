package com.nxc.pojo;

import com.nxc.enums.RoleEnum;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(length = 300)
    private String address;

    @Column(length = 12)
    private String phone;

    @Column(length = 300)
    private String avatar;

    @Column(length = 200)
    private String email;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Column(name = "updated_date", insertable = false)
    private Date updatedDate;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private Account account;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private Supplier supplier;

    @Valid
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private Carrier carrier;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private Set<Order> orders;

    @PrePersist
    protected void onCreate() {
        this.isDeleted = false;
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
