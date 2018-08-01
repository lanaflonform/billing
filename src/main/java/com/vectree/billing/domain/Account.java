package com.vectree.billing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Account implementation.
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Data
@RequiredArgsConstructor
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
}
