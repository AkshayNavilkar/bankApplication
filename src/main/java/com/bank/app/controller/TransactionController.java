package com.bank.app.controller;

import com.bank.app.impl.TransactionServiceImpl;
import com.bank.app.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction newTransaction) throws ServerException {
        Transaction transaction = transactionService.addTransaction(newTransaction);
        transactionService.isStatus();
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("transaction/accountNumber/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionByAccno(@PathVariable Integer accountNumber) {
        return new ResponseEntity<>(transactionService.getTransactionByAccountNo(), HttpStatus.OK);
    }


}
