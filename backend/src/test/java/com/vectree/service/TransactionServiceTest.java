package com.vectree.service;

import com.vectree.domain.*;
import lombok.val;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenUpdateStatusOfTransactionShouldCheckThatIsWasUpdated() {
        val donorWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        val recipientWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        val donor = new User(1L, "donor", "donorpwd", donorWallet, roles);
        val recipient = new User(2L, "recipient", "recepientpwd", recipientWallet, roles);
        userService.registerOrUpdate(donor);
        userService.registerOrUpdate(recipient);
        val transaction = new Transaction(donor, recipient, new BigDecimal("100.00"));
        transactionService.updateStatus(transaction, TransactionStatus.CREATED);
        assertThat(transaction.getStatus(), is(TransactionStatus.CREATED));
    }

    @Test
    public void whenRegisterTransactionShouldCheckThatTransactionWasRegistered() {
        val donorWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        val recipientWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        val donor = new User(1L, "donor", "donorpwd", donorWallet, roles);
        val recipient = new User(2L, "recipient", "recepientpwd", recipientWallet, roles);
        userService.registerOrUpdate(donor);
        userService.registerOrUpdate(recipient);
        val transaction = new Transaction(donor, recipient, new BigDecimal("100.00"));
        transactionService.register(transaction);
        val actualTransaction = transactionService.getById(transaction.getId());
        assertThat(actualTransaction.getId(), is(transaction.getId()));
    }

    @Test
    public void whenTryGetNotExistTransactionShouldCheckThatThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Transaction with give id not exits");

        val donorWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        val recipientWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        val donor = new User(1L, "donor", "donorpwd", donorWallet, roles);
        val recipient = new User(2L, "recipient", "recepientpwd", recipientWallet, roles);
        userService.registerOrUpdate(donor);
        userService.registerOrUpdate(recipient);
        transactionService.getById(-1L);
    }

    @Test
    public void whenTryPerformTransactionWithNullDonorAndRecipientShouldCheckThatExceptionThrowed() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Donor and recipient must be not null!");

        val transaction = new Transaction(null, null, new BigDecimal("100.00"));
        transactionService.register(transaction);
        transactionService.perform(transaction);
    }

    @Test
    public void whenTryPerformTransactionWithAmountBiggerThatHaveShouldCheckThatThrowException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Transaction could not perform because is not enough money on debit at the donor wallet");

        val donorWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        val recipientWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        val donor = new User(1L, "donor", "donorpwd", donorWallet, roles);
        val recipient = new User(2L, "recipient", "recepientpwd", recipientWallet, roles);
        userService.registerOrUpdate(donor);
        userService.registerOrUpdate(recipient);
        val transaction = new Transaction(donor, recipient, new BigDecimal("150.00"));
        transactionService.register(transaction);
        transactionService.perform(transaction);
    }

    @Test
    public void whenAllConditionTrueTransactionMustBePerformedShouldCheckIt() {
        val donorWallet = new Wallet(new BigDecimal("200.00"), new BigDecimal("100.00"));
        val recipientWallet = new Wallet(new BigDecimal("100.00"), new BigDecimal("100.00"));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        val donor = new User(1L, "donor", "donorpwd", donorWallet, roles);
        val recipient = new User(2L, "recipient", "recepientpwd", recipientWallet, roles);
        userService.registerOrUpdate(donor);
        userService.registerOrUpdate(recipient);
        val transaction = new Transaction(donor, recipient, new BigDecimal("10.00"));
        transactionService.register(transaction);
        transactionService.perform(transaction);
        assertThat(transaction.getStatus(), is(TransactionStatus.SUCCESS));
        assertThat(donorWallet.getDebit(), is(new BigDecimal("190.00")));
        assertThat(recipientWallet.getDebit(), is(new BigDecimal("110.00")));
    }
}