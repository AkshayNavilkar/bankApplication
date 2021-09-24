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
        account.setUser_id(account.getUser_id());

        return accountRepository.save(account);
    }

    @Override
    public Account depositBalance(Account account, float balance) {
        Account account1 = new Account();
        float updateBalance = account1.getBalance()+balance;
        account.setAccount_type(account.getAccount_type());
        account.setBalance(account.getBalance());
        account.setUser_id(account.getUser_id());
        account.setBalance(updateBalance);
        account.setIFSC(account.getIFSC());
        return accountRepository.save(account1);
    }

    @Override
    public Account getByAccountNumber(Integer accountNumber) {
        Account account = new Account();
        if(account.getAccount_no()==accountNumber) {
            return accountRepository.findById(accountNumber).get();
        }
        else {
            throw new AccountNotFoundException("Account doesn't exist",accountNumber);
        }
    }

    @Override
    public Account getByUserId(Integer userId) {
        return accountRepository.getAllAccountByUserId(userId);
    }
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public float getBalanceOfUser(Integer userId, Integer accountNumber) {
        Account account = new Account();
        if(account.getAccount_no()==accountNumber) {
            float accountBalance= accountRepository.getBalanceOfUser(userId,accountNumber);
            return accountBalance;
        }
        else
            throw new AccountNotFoundException("Account doesn't exist",accountNumber);
    }

}
