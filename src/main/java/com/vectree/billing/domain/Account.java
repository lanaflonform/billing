package com.vectree.billing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Account implementation.
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Data
@NoArgsConstructor(force = true)
public class Account {

    /**
     * Unique per each account number.
     */
    private final int id;

    /**
     * Debit account.
     */
    private final BigDecimal debit;

    /**
     * Credit account.
     */
    private final BigDecimal credit;

    /**
     * Create a new account.
     * @param id unique per each account number.
     * @param debit account.
     * @param credit account.
     */
    public Account(int id, BigDecimal debit, BigDecimal credit) {
        this.id = id;
        this.debit = debit;
        this.credit = credit;
    }
}
