package com.bank.app.service;

import com.bank.app.impl.TransactionServiceImpl;
import com.bank.app.model.Account;
import com.bank.app.model.Transaction;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;


    @BeforeEach
    public void setUp() {
        when(transactionRepository.save(Mockito.any())).thenReturn(dummyTransaction());
           }

    @Test
    public void closingBalance() throws Exception {
        Transaction transaction = dummyTransaction();
        Account account = dummyAccount();
        Optional<Account> optionalAccount = Optional.of(account);
        when(transactionRepository.getTransactionByAccNoTransactionId(Mockito.any(),Mockito.any())).thenReturn( dummyTransaction());
        when(accountRepository.findById(Mockito.any())).thenReturn(optionalAccount);
        when(transactionRepository.save(Mockito.any())).thenReturn(dummyTransactionUpdateBalance());

        Assertions.assertEquals(transactionService.closingBalance(100000111,1).getClosingBalance(),account.getBalance());
    }



    @Test
    public void addTransactionTest()  {
        Transaction transaction = transactionService.addTransaction(dummyTransaction());
        Assertions.assertNotNull(transaction.getTransaction_type());
        Assertions.assertNotNull(transaction.getTransactionAmount());
        Assertions.assertNotNull(transaction.getAccount_no());
        Assertions.assertNotNull(transaction.getBeneficiaryAccount_no());
        Assertions.assertNotNull(transaction.getClosingBalance());
    }

    private static List<Transaction> dummyTransactionList() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1,Transaction.StatusEnum.debit ,100000111,500F,100000112,0F));
        transactionList.add(new Transaction(2,Transaction.StatusEnum.credit ,100000111,500F,100000112,0F));
        transactionList.add(new Transaction(3,Transaction.StatusEnum.debit ,100000112,500F,100000111,0F));
        transactionList.add(new Transaction(4,Transaction.StatusEnum.credit ,100000112,500F,100000111,0F));
        return transactionList;
    }

    private static Transaction dummyTransactionUpdateBalance() {
        Transaction transaction = dummyTransaction();
        transaction.setClosingBalance(1500F);
        return transaction;
    }
    private static Account dummyAccount() {
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(1500F);
        account.setUser_name("Soumya7895");
        account.setIFSC("BANK0001235");
        return account;
    }
    private static Transaction dummyTransaction() {
        return new Transaction(1, Transaction.StatusEnum.credit, 100000111, 500F, 100000112, 0F);
    }
}
