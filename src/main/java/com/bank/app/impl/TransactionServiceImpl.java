package com.bank.app.impl;

import com.bank.app.exception.AccountNotFoundException;
import com.bank.app.exception.InsufficientBalanceException;
import com.bank.app.model.Account;
import com.bank.app.model.Transaction;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.TransactionRepository;
import com.bank.app.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;


    public Transaction closingBalance(Integer accountNo,Integer id) {
        Transaction transaction = transactionRepository.getTransactionByAccNoTransactionId(id,accountNo);
        Account account = accountRepository.findById(accountNo).get();
        transaction.setClosingBalance(account.getBalance());
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

    public void isStatus(Integer transactionId,Integer accountNo)  {
        Transaction transaction = transactionRepository.findById(transactionId).get();
        Account account = accountRepository.findById(accountNo).get();
        String x = transaction.getTransaction_type()+"";
        if(x.equals("debit"))
            debit(transactionId, transaction.getAccount_no(),transaction.getBeneficiaryAccount_no());
        if(x.equals("credit"))
            credit(transactionId,transaction.getAccount_no(),transaction.getBeneficiaryAccount_no());
    }

    @Override
    public String debit(Integer transactionId , Integer accountNo,Integer beneficiaryAccNo )  {
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
                throw new InsufficientBalanceException("You don't have sufficient balance" + "Current balance is" + currentAmount);
            }
        } else{
            throw new AccountNotFoundException("Account doesn't exist"+ accountNo);
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
            throw new AccountNotFoundException("Account doesn't exist"+ accountNo);
        }

    }

        public List<Transaction> getTransactionByAccountNo(Integer accountNo) {
        return transactionRepository.getTransactionByAccNo(accountNo);
    }

}
