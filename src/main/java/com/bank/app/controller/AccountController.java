package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private IAccountService accountService;

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

    @PutMapping("account/deposit")
    public ResponseEntity<Account> deposit(@RequestBody Account newAccount , float balance) {
        Account account = accountService.depositBalance(newAccount,balance);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("account/userId/{id}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable Integer id) {
        Account accounts = accountService.getByUserId(id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("accounts")
    public ResponseEntity<List<Account>> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("account/balance/{userId}/{accountNo}")
    public float getBalanceOfUser(@PathVariable Integer userId, @PathVariable Integer accountNo) {
        float balanceOfUser = accountService.getBalanceOfUser(userId, accountNo);
        return balanceOfUser;
    }

}