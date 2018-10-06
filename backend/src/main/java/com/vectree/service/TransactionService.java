package com.vectree.service;

import com.vectree.domain.Transaction;
import com.vectree.domain.TransactionStatus;
import com.vectree.domain.User;
import com.vectree.domain.Wallet;
import com.vectree.repository.interfaces.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private TransactionRepository repository;

    @Autowired
    private TransactionService(TransactionRepository repo) {
        repository = repo;
    }

    public void register(Transaction transaction) {
        transaction.setStatus(TransactionStatus.CREATED);
        repository.save(transaction);
    }

    public void updateStatus(Transaction transaction, TransactionStatus status) {
        transaction.setStatus(status);
        repository.save(transaction);
    }

    public Transaction getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with give id not exits"));
    }

    public void perform(Transaction transaction) {
        User donor = transaction.getDonor();
        User recipient = transaction.getRecipient();

        if (donor == null || recipient == null) {
            transaction.setStatus(TransactionStatus.FAIL);
            throw new IllegalArgumentException("Donor and recipient must be not null!");
        }
        Wallet from = donor.getWallet();
        Wallet to = recipient.getWallet();

        BigDecimal amount = transaction.getAmount();
        if (amount.compareTo(from.getDebit()) >= 0) {
            transaction.setStatus(TransactionStatus.FAIL);
            throw new IllegalArgumentException("Transaction could not perform because is not enough money on debit at the donor wallet");
        }

        BigDecimal balance = from.getDebit().subtract(amount);
        from.setDebit(balance);
        to.setDebit(to.getDebit().add(amount));

        transaction.setStatus(TransactionStatus.SUCCESS);
    }


}
