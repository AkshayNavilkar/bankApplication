package com.bank.app.impl;

import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import com.bank.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account)
    {
        account.setAccount_type(account.getAccount_type());
        account.setBalance(account.getBalance());
        account.setUser_name(account.getUser_name());

        return accountRepository.save(account);
    }
}
