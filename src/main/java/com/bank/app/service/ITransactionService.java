package com.bank.app.service;

import com.bank.app.model.Transaction;

import java.rmi.ServerException;
import java.util.List;

public interface ITransactionService {

    Transaction addTransaction(Transaction transaction);
    String debit(Integer transactionId, Integer accountNo,Integer beneficiaryAccNo) ;
    String credit(Integer transactionId, Integer accountNo,Integer beneficiaryAccNo);
    List<Transaction> getTransactionByAccountNo(Integer accountNo);


}
