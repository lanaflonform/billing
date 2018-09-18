package com.vectree.controller;

import com.vectree.domain.Transaction;
import com.vectree.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService service) {
        transactionService = service;
    }

    public void perform(Transaction transaction) {
        transactionService.perform(transaction);
    }
}
