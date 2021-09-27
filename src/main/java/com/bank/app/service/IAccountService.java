package com.bank.app.service;

import com.bank.app.model.Account;

import java.util.List;

public interface IAccountService {

    public Account createAccount(Account account);
    public List<Account> getAllAccounts();
    public Account getAccountByAccNo(Integer accno);

}
