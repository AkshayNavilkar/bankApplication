package com.bank.app.controller;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {AccountControllerTest.class})
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AccountServiceImpl accountService;
    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }
    @Test
    public void getBalanceOfUserTest() throws Exception {
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(2000f);
        account.setUser_name("jayshree14");
        account.setIFSC("BANK0005943");
        when(accountService.getBalanceOfUser("jayshree14",100000111)).thenReturn(2000f);

        this.mockMvc.perform(get("/api/account/balance/jayshree14/100000111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(account.getBalance()))
                .andDo(print());

    }

    @Test
    public void depositBalance() throws Exception {
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setBalance(2000F);
        when(accountService.depositBalance(100000111,100F)).thenReturn(account);

        ObjectMapper mapper = new ObjectMapper();

        String jsonbody=mapper.writeValueAsString(account);
        System.out.println(jsonbody);

        this.mockMvc.perform(patch("/api/account/deposit/100000111/100F")
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}



