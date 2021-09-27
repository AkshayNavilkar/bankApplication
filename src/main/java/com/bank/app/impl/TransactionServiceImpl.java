package com.bank.app.impl;

import com.bank.app.exceptions.AccountNotFoundException;
import com.bank.app.model.Account;
import com.bank.app.model.Transaction;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.TransactionRepository;
import com.bank.app.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;


@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;


    public Transaction closingBalance(Integer accountNo) {
        Transaction transaction = transactionRepository.getTransactionByAccountNo(accountNo);
        transaction.setClosingBalance(accountRepository.findById(accountNo).get().getBalance());
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {

        transaction.setTransaction_type(transaction.getTransaction_type());
        transaction.setTransactionAmount(transaction.getTransactionAmount());
        transaction.setAccount_no(transaction.getAccount_no());
        transaction.setBeneficiaryAccount_no(transaction.getBeneficiaryAccount_no());
        transaction.setClosingBalance((float) 0);
        return transactionRepository.save(transaction);
    }


    @Transactional
    public void isStatus(Integer transactionId,Integer accountNo) throws ServerException {
        Transaction transaction=transactionRepository.findById(transactionId).get();
        Account account = accountRepository.findById(accountNo).get();
        if(!transaction.getTransaction_type())
            debit(transactionId, transaction.getAccount_no(),transaction.getBeneficiaryAccount_no());
        else
            credit(transactionId,transaction.getAccount_no(),transaction.getBeneficiaryAccount_no());
    }

    @Override
    public String debit(Integer transactionId , Integer accountNo,Integer beneficiaryAccNo ) throws ServerException {
        Transaction transaction=transactionRepository.findById(transactionId).get();
        Account account = accountRepository.findById(accountNo).get();
        Account account1 = accountRepository.findById(beneficiaryAccNo).get();
        if(account.getAccount_no().equals(accountNo) && account1.getAccount_no().equals(beneficiaryAccNo)) {
            Float currentAmount = accountRepository.findById(accountNo).get().getBalance();
            Float currentAmount2 = accountRepository.findById(beneficiaryAccNo).get().getBalance();
            Float transactionAmount = transaction.getTransactionAmount();
            if (currentAmount >= transactionAmount) {
                currentAmount -= transactionAmount;
                account.setBalance(currentAmount);
                accountRepository.save(account);
                transaction.setClosingBalance(currentAmount);
                transactionRepository.save(transaction);
                currentAmount2 += transactionAmount;
                account1.setBalance(currentAmount2);
                accountRepository.save(account1);
                return transactionAmount + " Rs debited.";
            } else {
                throw new ServerException("You don't have sufficient balance" + "Current balance is" + currentAmount);
            }
        } else{
            throw new AccountNotFoundException("Account doesn't exist",accountNo);
        }

    }

    @Override
    public String credit( Integer transactionId,  Integer accountNo, Integer beneficiaryAccNo ) {
        Transaction transaction = transactionRepository.findById(transactionId).get();
        Account account = accountRepository.findById(accountNo).get();
        Account account1 = accountRepository.findById(beneficiaryAccNo).get();
        if(account.getAccount_no().equals(accountNo) && account1.getAccount_no().equals(beneficiaryAccNo)) {
            Float currentAmount = accountRepository.findById(accountNo).get().getBalance();
            Float currentAmount2 = accountRepository.findById(beneficiaryAccNo).get().getBalance();
            Float transactionAmount = transaction.getTransactionAmount();
            currentAmount += transactionAmount;
            account.setBalance(currentAmount);
            transaction.setClosingBalance(currentAmount);
            currentAmount2 -= transactionAmount;
            account1.setBalance(currentAmount2);
            transactionRepository.save(transaction);
            accountRepository.save(account);
            accountRepository.save(account1);
            return transactionAmount + " Rs credited.";
        }else{
            throw new AccountNotFoundException("Account doesn't exist",accountNo);
        }

    }

        public Transaction getTransactionByAccountNo(Integer accountNo) {
        return transactionRepository.getTransactionByAccountNo(accountNo);
    }

}
