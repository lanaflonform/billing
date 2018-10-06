package com.vectree.domain;

import lombok.val;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {

    @Test
    public void whenTrySetAllFieldOfTransactionShouldCheckThatWasSaved() {
        val transaction = new Transaction();
        val donor = new User();
        val recipient = new User();
        val amount = new BigDecimal("100.00");
        val timestamp = new Timestamp(System.currentTimeMillis());
        val status = TransactionStatus.SUCCESS;
        transaction.setDonor(donor);
        transaction.setRecipient(recipient);
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        transaction.setStatus(status);

        assertThat("Donor of the transaction is not set.", transaction.getDonor(), is(donor));
        assertThat("Recipient of the transaction is not set.", transaction.getRecipient(), is(recipient));
        assertThat("Amount of the transaction is not set.", transaction.getAmount(), is(amount));
        assertThat("Timestamp of the transaction is not set.", transaction.getTimestamp(), is(timestamp));
        assertThat("Status of the transaction is not set.", transaction.getStatus(), is(status));
    }
}