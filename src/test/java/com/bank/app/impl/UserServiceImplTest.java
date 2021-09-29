package com.bank.app.impl;

import com.bank.app.model.Account;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest(classes=UserServiceImplTest.class)
public class UserServiceImplTest {


    @Test
    public void validateUserByEmail() {
    }

    @Test
    public void validateLogin() {

    }
}