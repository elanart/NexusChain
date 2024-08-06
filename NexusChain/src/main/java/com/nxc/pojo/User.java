package com.nxc.pojo;

import com.nxc.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", insertable = false)
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Valid
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "user")
    private Account account;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "user")
    private Set<Order> orders;

    @PrePersist
    protected void onCreate() {
        this.isDeleted = false;
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
