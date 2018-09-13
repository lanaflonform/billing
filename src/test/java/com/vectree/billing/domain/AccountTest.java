package com.vectree.billing.domain;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Account unit test check that all works fine.
 *
 * @author Egor Voronyansky
 * @version 0.1
 */
public class AccountTest {
    /**
     * System under test.
     */
    private Account account = new Account(1L, new BigDecimal(100), new BigDecimal(100));
    private Random random = new Random();

    /**
     * When try get id from account should check that id is correct.
     */
    @Test
    public void whenTryGetIdFromAccountShouldCheckThatIdCorrect() {
        assertThat(account.getId(), is(1L));
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
    public void whenTryGetCreditAccountShouldCheckThatAccountReturnTrueValue() {
        assertThat(account.getCredit(), is(new BigDecimal(100)));
    }

    /**
     * Test toString() is not null
     */
    @Test
    public void whenTryGetToStringAccountShouldCheckThatAccountReturnStringValue() {
        assertThat(account.toString(), instanceOf(String.class));
    }

    /**
     * Test toString format
     */
    @Test
    public void whenTryGetToStringAccountShouldCheckThatAccountReturnTrueValue() {
        assertThat(account.toString(), is("Account {id=1 debit=100,00 credit=100,00}"));
    }

    /**
     * Test x.equals(y) = y.equals(x)
     */
    @Test
    public void whenTryGetEqualsAccountsShouldCheckThatSimmetric() {
        for (int i = 0; i < 1000; i++) {
            Long value = random.nextLong();
            val x = new Account(value, new BigDecimal(value), new BigDecimal(value));
            val y = new Account(value, new BigDecimal(value), new BigDecimal(value));
            Assert.assertTrue(x.equals(y) && y.equals(x));
        }
    }

    /**
     * Test x.hashCode() == y.hashCode()
     */
    @Test
    public void whenTryGetHashCodeXYAccountsShouldCheckThatEqualYX() {
        for (int i = 0; i < 1000; i++) {
            Long value = random.nextLong();
            val x = new Account(value, new BigDecimal(value), new BigDecimal(value));
            val y = new Account(value, new BigDecimal(value), new BigDecimal(value));
            Assert.assertTrue(x.hashCode() == y.hashCode());
        }
    }
}