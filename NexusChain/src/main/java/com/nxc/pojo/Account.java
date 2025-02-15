package com.nxc.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = 1, max = 50, message = "{account.username.sizeMsg}")
    @Pattern(regexp = "^[a-zA-Z0-9.]{3,}$", message = "{account.username.patternMsg}")
    private String username;

    @NotNull
    @Column(length = 300, nullable = false)
    @NotEmpty(message = "{account.password.notnull.errMsg}")
    private String password;

    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId
    @OneToOne(optional = false)
    private User user;
}
