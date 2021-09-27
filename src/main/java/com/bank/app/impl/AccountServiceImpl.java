package com.bank.app.impl;

import com.bank.app.exceptions.AccountNotFoundException;
import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import com.bank.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        account.setIFSC("BANK0005943");
        return accountRepository.save(account);
    }

    @Override
    public Account depositBalance(Integer accountNo,Float balance) {
        Account account = accountRepository.findById(accountNo).get();
        account.setBalance(account.getBalance()+balance);
        return accountRepository.save(account);
    }

    @Override
    public Account getByAccountNumber(Integer accountNumber) {
        Account account = accountRepository.findById(accountNumber).get();
        if(account.getAccount_no().equals(accountNumber)) {
            return accountRepository.findById(accountNumber).get();
        }
        else {
            throw new AccountNotFoundException("Account doesn't exist",accountNumber);
        }
    }

    @Override
    public Account getByUserName(String userName) {
        return accountRepository.getAllAccountByUserName(userName);
    }
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public float getBalanceOfUser(String userName, Integer accountNumber) {
        Account account = accountRepository.findById(accountNumber).get();
        if(account.getAccount_no().equals(accountNumber)) {
            float accountBalance= accountRepository.getBalanceOfUser(userName,accountNumber);
            return accountBalance;
        }
        else
            throw new AccountNotFoundException("Account doesn't exist",accountNumber);
    }



}
