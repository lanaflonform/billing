package com.vectree.billing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Account implementation.
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Data
@Entity
@Table(name = "accounts", catalog = "users")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Account {

    /**
     * Unique per each account number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    /**
     * Debit account.
     */
    @Column(name = "debit")
    private final BigDecimal debit;

    /**
     * Credit account.
     */
    @Column(name = "credit")
    private final BigDecimal credit;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
