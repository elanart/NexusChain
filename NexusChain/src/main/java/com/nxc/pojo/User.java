package com.nxc.pojo;

import com.nxc.enums.RoleEnum;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{user.email.patternMsg}")
    private String email;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @Builder.Default
    @Column(name = "confirm")
    private Boolean isConfirm = false;

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
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
