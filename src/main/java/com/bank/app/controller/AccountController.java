package com.bank.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private UserDetailService myUserDetailService;
	 * 
	 * @Autowired private JwtUtil jwtUtil;
	 */	

    @PostMapping("create")
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {
        Account account = accountService.createAccount(newAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("get/accountNumber/{number}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Integer number) {
        Account account = accountService.getByAccountNumber(number);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("update/deposit/{accountNo}/{balance}")
    public ResponseEntity<Account> deposit(@Valid @PathVariable Integer accountNo,@PathVariable Float balance) {
        Account account = accountService.depositBalance(accountNo,balance);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("get/userName/{username}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable String username) {
        Account accounts = accountService.getByUserName(username);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("getall")
    public ResponseEntity<List<Account>> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("get/balance/{userName}/{accountNo}")
    public String getBalanceOfUser(@PathVariable String userName, @PathVariable Integer accountNo) {
        Float balanceOfUser = accountService.getBalanceOfUser(userName, accountNo);
        return "Your Available balance is: "+balanceOfUser;
    }


}