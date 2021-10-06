package com.bank.app.controller;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;
import com.bank.app.model.AuthenticationRequest;
import com.bank.app.model.AuthenticationResponse;
import com.bank.app.security.UserDetailService;
import com.bank.app.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

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
    public String getBalanceOfUser(@PathVariable String userName, @PathVariable Integer accountNo) {
        Float balanceOfUser = accountService.getBalanceOfUser(userName, accountNo);
        return "Your Available balance is: "+balanceOfUser;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest auth) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect UserName or Password",e);

        }
        final UserDetails userDetails=myUserDetailService.loadUserByUsername(auth.getUserName());
        final String jwt= jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}