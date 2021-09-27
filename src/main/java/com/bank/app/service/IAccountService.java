package com.bank.app.service;

import com.bank.app.model.Account;

import java.util.List;

public interface IAccountService {

    Account createAccount(Account account);
    Account getByAccountNumber(Integer accountNumber);
    Account getByUserName(String userName);
    Account depositBalance(Integer accountNo, Float balance);
    List<Account> getAllAccount();
    float getBalanceOfUser(String userName,Integer accountNumber);

}
