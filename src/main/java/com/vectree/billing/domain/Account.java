package com.vectree.billing.domain;

import java.math.BigDecimal;

/**
 * Account implementation.
 * @author Egor Voronyansky
 * @version 0.1.
 */
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

    /**
     * Return unique per each account number.
     * @return unique number of this account.
     */
    public int getId() {
        return id;
    }

    /**
     * Return value of the debit account.
     * @return value of the debit account.
     */
    public BigDecimal getDebit() {
        return debit;
    }

    /**
     * Return value of the credit account.
     * @return value of credit account.
     */
    public BigDecimal getCredit() {
        return credit;
    }
}
