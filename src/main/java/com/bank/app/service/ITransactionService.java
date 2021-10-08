package com.bank.app.service;

import java.util.List;

import com.bank.app.model.Transaction;

public interface ITransactionService {

    Transaction addTransaction(Transaction transaction);
    String debit(Integer transactionId, Integer accountNo,Integer beneficiaryAccNo) ;
    String credit(Integer transactionId, Integer accountNo,Integer beneficiaryAccNo);
    List<Transaction> getTransactionByAccountNo(Integer accountNo);


}
