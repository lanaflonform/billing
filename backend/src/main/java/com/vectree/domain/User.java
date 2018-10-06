package com.vectree.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToOne(targetEntity = Wallet.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Role> roles;

    public User(String email, String password, Wallet wallet, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.wallet = wallet;
        this.roles = roles;
    }

    public User(Long id, String email, String password, Wallet wallet, Set<Role> roles) {
        this(email, password, wallet, roles);
        this.id = id;
    }

}
