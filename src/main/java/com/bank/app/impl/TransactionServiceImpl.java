package com.bank.app.impl;

import com.bank.app.exceptions.AccountNotFoundException;
import com.bank.app.model.Account;
import com.bank.app.model.Transaction;
import com.bank.app.repository.TransactionRepository;
import com.bank.app.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountServiceImpl accountService;

    @Override
    public Transaction addTransaction(Transaction transaction) {
        transaction.setTransaction_type(transaction.getTransaction_type());
        transaction.setTransactionAmount(transaction.getTransactionAmount());
        transaction.setAccount_no(transaction.getAccount_no());
        transaction.setBeneficiaryAccount_no(transaction.getBeneficiaryAccount_no());
        return transactionRepository.save(transaction);
    }

    public void isStatus() throws ServerException {
        Transaction transaction=new Transaction();
        Account account=new Account();
        if(transaction.getTransaction_type()==false)
            debit(account.getUser_id(),account.getAccount_no(),transaction.getBeneficiaryAccount_no(), transaction.getTransactionAmount());
        else
            credit(account.getUser_id(),account.getAccount_no(),transaction.getBeneficiaryAccount_no(), transaction.getTransactionAmount());
    }

    @Override
    public String debit(Integer userId , Integer accountNo,Integer beneficiaryAccNo ,float amount) throws ServerException {
        Transaction transaction = new Transaction();
        if(transaction.getAccount_no()==accountNo && transaction.getBeneficiaryAccount_no()==beneficiaryAccNo) {
            float currentAmount = accountService.getBalanceOfUser(userId, accountNo);
            float currentAmount2 = accountService.getBalanceOfUser(userId, beneficiaryAccNo);
            float transactionAmount = transaction.getTransactionAmount();
            amount = transactionAmount;
            if (currentAmount >= transactionAmount) {
                currentAmount -= transactionAmount;
                currentAmount2 += transactionAmount;
                return transactionAmount + " Rs debited.";
            } else {
                throw new ServerException("You don't have sufficient balance" + "Current balance is" + currentAmount);
            }
        } else{
            throw new AccountNotFoundException("Account doesn't exist",accountNo);
        }

    }

    @Override
    public String credit(Integer userId , Integer accountNo, Integer beneficiaryAccNo ,float amount) {
        Transaction transaction = new Transaction();
        if(transaction.getAccount_no()==accountNo && transaction.getBeneficiaryAccount_no()==beneficiaryAccNo) {
            float currentAmount = accountService.getBalanceOfUser(userId, accountNo);
            float currentAmount2 = accountService.getBalanceOfUser(userId, beneficiaryAccNo);
            float transactionAmount = transaction.getTransactionAmount();
            amount = transactionAmount;
            currentAmount += transactionAmount;
            currentAmount2 -= transactionAmount;
            return transactionAmount + " Rs credited.";
        }else{
            throw new AccountNotFoundException();
        }

    }

    @Override
    public List<Transaction> getTransactionByAccountNo() {
        return transactionRepository.getTransactionByAccountNo();
    }

}
