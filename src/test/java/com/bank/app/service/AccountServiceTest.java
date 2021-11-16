package com.bank.app.service;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(classes= {AccountServiceTest.class})
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    public void createAccountTest() {
        Account account = new Account();
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(1000f);
        account.setUser_name("DeepakBhuyan2697");
        account.setIFSC("BANK0005943");

        when(accountRepository.save(account)).thenReturn(account);
        assertEquals(account, accountService.createAccount(account));
    }

    @Test
    public void depositBalanceTest() {
        Account account = new Account();
        Float addBalance = 500f;
        account.setAccount_no(100000111);
        account.setBalance(1000f);

        Optional<Account> account1 = Optional.ofNullable(account);
        when(accountRepository.findById(account1.get().getAccount_no())).thenReturn(account1);
        when(accountRepository.save(account1.get())).thenReturn(account1.get());

        Account newacc = accountService.depositBalance(account1.get().getAccount_no(), addBalance);
        assertEquals(1500f, newacc.getBalance());
    }

    @Test
    public void getByAccountNumberTest(){
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(2000f);
        account.setUser_name("DeepakBhuyan2697");
        account.setIFSC("BANK0005943");

        Optional<Account> account1 = Optional.ofNullable(account);
        when(accountRepository.findById(100000111)).thenReturn(account1);
        assertEquals(account, accountService.getByAccountNumber(100000111));
    }

    @Test
    public void getBalanceOfUserTest() {
        Account account = new Account();
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(1000f);
        account.setAccount_no(100000111);
        account.setUser_name("DeepakBhuyan2697");
        account.setIFSC("BANK0005943");

        Optional<Account> account1 = Optional.ofNullable(account);

//        when(accountRepository.findById(account1.get().getAccount_no())).thenReturn(account1);
//        when(accountRepository.getBalanceOfUser(account1.get().getUser_name(), account1.get().getAccount_no())).thenReturn(account1.get().getBalance());
//        Float balance = accountService.getBalanceOfUser(account1.get().getUser_name(), account1.get().getAccount_no());

        when(accountRepository.findById(100000111)).thenReturn(account1);
        when(accountRepository.getBalanceOfUser("DeepakBhuyan2697",100000111)).thenReturn(1000f);
        Float balance=accountService.getBalanceOfUser("DeepakBhuyan2697",100000111);
        assertEquals(1000f, balance);
    }
    @Test
    public void findAllAccountTest() {
        List<Account> accounts = new ArrayList<Account>();
        Account account1 = new Account();
        account1.setAccount_no(100000111);
        account1.setAccount_type(Account.StatusEnum.savings);
        account1.setBalance(20001f);
        account1.setUser_name("jayshree14");
        account1.setIFSC("BANK0005943");
        Account account2 = new Account();
        account2.setAccount_no(100000112);
        account2.setAccount_type(Account.StatusEnum.savings);
        account2.setBalance(3000f);
        account2.setUser_name("deepak123");
        account2.setIFSC("BANK0005943");
        accounts.add(account1);
        accounts.add(account2);

        when(accountRepository.findAll()).thenReturn(accounts);
        assertEquals(accounts,accountService.getAllAccount());
    }
    @Test
    public void getByUserNameTest(){
        Account account = new Account();
        account.setUser_name("jayshree14");
        when(accountRepository.getAllAccountByUserName("jayshree14")).thenReturn(account);
        assertEquals(account,accountService.getByUserName("jayshree14"));

    }

    @Test
    public void validAccountNumberTest(){
        Account account = new Account();
        account.setAccount_no(100000111);
        Optional<Account> account1 = Optional.ofNullable(account);

        when(accountRepository.findById(100000111)).thenReturn(account1);
        assertEquals(account, accountService.getByAccountNumber(100000111),"Account Number is valid");
    }
    @Test
    public void inValidAccountNumberTest(){

// preparing dummy data
        Account account = new Account();
        account.setAccount_no(100000111);
        Optional<Account> account1 = Optional.of(account);

// adding behavior
        when(accountRepository.findById(100000111)).thenReturn(account1);

// checking behavior
        Account actualAccount = accountService.getByAccountNumber(100000111);

// creating expected data
        Account expectedAccount = new Account();
        expectedAccount.setAccount_no(100000110);

        assertNotEquals(expectedAccount.getAccount_no(),actualAccount.getAccount_no());
    }


}