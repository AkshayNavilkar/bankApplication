package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import com.bank.app.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("account")
    public @ResponseBody
    Account createAccount(@RequestBody Account account)
    {
        return accountService.createAccount(account);
    }
}
