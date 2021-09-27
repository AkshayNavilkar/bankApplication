package com.bank.app.impl;

import com.bank.app.exception.ValidationFailedException;
import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import com.bank.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        Account  newAccount = null;
        try{
            newAccount = accountRepository.save(account);
        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }catch(NoSuchElementException nsee){
            throw new ValidationFailedException("Invalid Username !!!");
        }catch(TransactionSystemException tse){
            throw new ValidationFailedException("Database getting Error !!!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return newAccount;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountByAccNo(Integer accno) {
        Account findAccount =  null;

        try{
            findAccount = accountRepository.findById(accno).get();
        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }catch(NoSuchElementException nsee){
            throw new ValidationFailedException("Invalid Account Number !!!");
        }
        return findAccount;
    }


}
