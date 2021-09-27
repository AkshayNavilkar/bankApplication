package com.bank.app.service;

import com.bank.app.model.Transaction;

import java.rmi.ServerException;

public interface ITransactionService {

    Transaction addTransaction(Transaction transaction);
    String debit(Integer transactionId, Integer accountNo,Integer beneficiaryAccNo) throws ServerException;
    String credit(Integer transactionId, Integer accountNo,Integer beneficiaryAccNo);
    Transaction getTransactionByAccountNo(Integer accountNo);


}
