package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
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

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/getAllAccount")
    public ResponseEntity<List<Account>> getAllAccount() {
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/getAllAccount/{accountno}")
    public ResponseEntity<Account> getAccountByAccNo(@PathVariable("accountno") Integer accountNo) {
        return new ResponseEntity<Account>(accountService.getAccountByAccNo(accountNo), HttpStatus.OK);
    }

}
