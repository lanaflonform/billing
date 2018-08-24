package com.vectree.billing.domain;

import lombok.Data;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Account implementation.
 *
 * @author Egor Voronyansky
 * @version 0.1.
 */
@Data
@Entity
@Table(name = "accounts")
public class Account {

    /**
     * Unique per each account number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billingSequence")
    @SequenceGenerator(sequenceName = "billing_sequence", allocationSize = 1, name = "billingSequence")
    private int id;

    /**
     * Debit account.
     */
    @Column(name = "debit")
    private BigDecimal debit;

    /**
     * Credit account.
     */
    @Column(name = "credit")
    private BigDecimal credit;

    /**
     * User assigned to account.
     */
    @OneToOne(optional = false, mappedBy = "account")
    private User user;

    public Account() {
        this.id = 0;
        this.debit = new BigDecimal("0.0");
        this.credit = new BigDecimal("0.0");
    }

    public Account(int id, BigDecimal debit, BigDecimal credit) {
        this.id = id;
        this.debit = debit;
        this.credit = credit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Account account = (Account) obj;
        return id == account.getId()
                && debit.equals(account.getDebit())
                && credit.equals(account.getCredit());
    }

    @Override
    public int hashCode() {
        return id + debit.hashCode() + credit.hashCode();
    }

    @Override
    public String toString() {
        return "Account {" +
                "id=" + id +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }
}
