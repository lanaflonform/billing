package com.vectree.billing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User of the system.
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Entity
@Table(name = "users", catalog = "accounts")
@Data @NoArgsConstructor
public class User {

    /**
     * Unique per user number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Email of the user.
     */
    @Column(name = "email")
    private String email;

    /**
     * Password of the user, must be crypt before adding to the database.
     */
    @Column(name = "password")
    private String password;

    /**
     * Wallet of the user.
     */
    @JoinColumn(name = "account_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}
