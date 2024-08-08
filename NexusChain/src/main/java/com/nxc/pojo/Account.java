package com.nxc.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    private Long id;

    @NotNull
    @Column(length = 50, nullable = false)
    private String username;

    @NotNull
    @Column(length = 300, nullable = false)
    private String password;

    @JsonIgnore
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId
    @OneToOne(optional = false)
    private User user;
}
