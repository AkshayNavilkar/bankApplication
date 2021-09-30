package com.bank.app.service;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = AccountServiceTest.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    public void findAllAccountTest() {

        List<Account> accounts = new ArrayList<Account>();
        Account account1 = new Account();
        account1.setAccount_no(100000111);
        account1.setAccount_type(Account.StatusEnum.savings);
        account1.setBalance(20001F);
        account1.setUser_name("jayshree14");
        account1.setIFSC("BANK0005943");
        Account account2 = new Account();
        account2.setAccount_no(100000112);
        account2.setAccount_type(Account.StatusEnum.savings);
        account2.setBalance(3000F);
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
        Optional<Account> account1 = Optional.ofNullable(account);

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
