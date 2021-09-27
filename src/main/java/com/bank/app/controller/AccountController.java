package com.bank.app.controller;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("account")
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {
        Account account = accountService.createAccount(newAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("account/accountNumber/{number}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Integer number) {
        Account account = accountService.getByAccountNumber(number);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("account/deposit/{accountNo}/{balance}")
    public ResponseEntity<Account> deposit(@Valid @PathVariable Integer accountNo,@PathVariable Float balance) {
        Account account = accountService.depositBalance(accountNo,balance);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("account/userName/{username}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable String username) {
        Account accounts = accountService.getByUserName(username);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("accounts")
    public ResponseEntity<List<Account>> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("account/balance/{userName}/{accountNo}")
    public float getBalanceOfUser(@PathVariable String userName, @PathVariable Integer accountNo) {
        float balanceOfUser = accountService.getBalanceOfUser(userName, accountNo);
        return balanceOfUser;
    }

}