package com.bank.app.service;

import java.util.List;

import com.bank.app.model.Account;

public interface IAccountService {

    Account createAccount(Account account);
    Account getByAccountNumber(Integer accountNumber);
    Account getByUserName(String userName);
    Account depositBalance(Integer accountNo, Float balance);
    List<Account> getAllAccount();
    Float getBalanceOfUser(String userName,Integer accountNumber);

}
