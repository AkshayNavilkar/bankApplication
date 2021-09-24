package com.bank.app.service;

import com.bank.app.model.Transaction;

import java.rmi.ServerException;
import java.util.List;

public interface ITransactionService {

    Transaction addTransaction(Transaction transaction);
    String debit(Integer userId , Integer accountNo,Integer beneficiaryAccNo, float amount) throws ServerException;
    String credit(Integer userId , Integer accountNo,Integer beneficiaryAccNo, float amount);
    List<Transaction> getTransactionByAccountNo();


}
