package com.vectree.billing.domain;

import org.junit.Test;
import java.math.BigDecimal;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Account unit test check that all works fine.
 * @author Egor Voronyansky
 * @version 0.1
 */
public class AccountTest {

    /**
     * System under test.
     */
    private Account account = new Account(1, new BigDecimal(100), new BigDecimal(100));


    /**
     * When try get id from account should check that id is correct.
     */
    @Test
    public void whenTryGetIdFromAccountShouldCheckThatIdCorrect() {
        assertThat(account.getId(), is(1));
    }

    /**
     * When try get debit account of the account should check that return true value.
     */
    @Test
    public void whenTryGetDebitAccountShouldCheckThatAccountReturnTrueValue() {
        assertThat(account.getDebit(), is(new BigDecimal(100)));
    }

    /**
     * When try get credit account of the account should check that return true value.
     */
    @Test
    public void getCredit() {
        assertThat(account.getCredit(), is(new BigDecimal(100)));
    }
}