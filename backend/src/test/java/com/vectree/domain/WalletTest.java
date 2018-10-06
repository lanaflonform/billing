package com.vectree.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WalletTest {

    @Test
    public void whenTrySetAllFieldsShouldCheckThatAllSaved() {
        Wallet wallet = new Wallet();
        wallet.setId(1L);
        wallet.setDebit(new BigDecimal("100.00"));
        wallet.setCredit(new BigDecimal("100.00"));

        assertThat("ID of the object are not equals.", wallet.getId(), is(1L));
        assertThat("Debit is not set correct.", wallet.getDebit().longValue(), is(new BigDecimal("100.00").longValue()));
        assertThat("Credit is not set correct.", wallet.getCredit().longValue(), is(new BigDecimal("100.00").longValue()));
    }
}