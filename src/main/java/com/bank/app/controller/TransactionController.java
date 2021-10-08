package com.bank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.impl.TransactionServiceImpl;
import com.bank.app.model.Transaction;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction newTransaction)  {
        Transaction transaction = transactionService.addTransaction(newTransaction);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
    @PatchMapping("update/{transactionId}/{accountNo}")
    public ResponseEntity<Transaction> updateBalance(@PathVariable Integer transactionId ,@PathVariable Integer accountNo) {
        transactionService.isStatus(transactionId,accountNo);
        Transaction transaction = transactionService.closingBalance(transactionId,accountNo);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    @GetMapping("get/accountNumber/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionByAccno(@PathVariable Integer accountNumber) {
        return new ResponseEntity<>(transactionService.getTransactionByAccountNo(accountNumber), HttpStatus.OK);
    }

}
