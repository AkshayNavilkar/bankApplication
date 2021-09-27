package com.bank.app.service;

import com.bank.app.model.Account;

import java.util.List;

public interface IAccountService {

    Account createAccount(Account account);
    Account getByAccountNumber(Integer accountNumber);
    Account getByUserId(Integer userId);
    Account depositBalance(Integer accountNo, Float balance);
    List<Account> getAllAccount();
    float getBalanceOfUser(Integer userId,Integer accountNumber);

}
