package com.bank.app.controller;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
    public void createAccountTest() throws Exception {
        Account account = new Account();
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(2000f);
        account.setUser_name("DeepakBhuyan2697");
        account.setIFSC("BANK0005943");

        when(accountService.createAccount(account)).thenReturn(account);
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(account);
        this.mockMvc.perform(post("/api/account")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());
        System.out.println("Account created Successfully");
    }

    @Test
    public void getByAccountNumberTest() throws Exception {
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(2000f);
        account.setUser_name("DeepakBhuyan2697");
        account.setIFSC("BANK0005943");

        when(accountService.getByAccountNumber(100000111)).thenReturn(account);
        this.mockMvc.perform(get("/api/account/accountNumber/100000111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".account_no").value(100000111))
                .andExpect(MockMvcResultMatchers.jsonPath(".account_type").value("savings"))
                .andExpect(MockMvcResultMatchers.jsonPath(".user_name").value("DeepakBhuyan2697"))
                .andExpect(MockMvcResultMatchers.jsonPath(".balance").value(2000.0))
                .andExpect(MockMvcResultMatchers.jsonPath(".ifsc").value("BANK0005943"))
                .andDo(print());
    }

    @Test
    public void getByUserNameTest() throws Exception {
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setAccount_type(Account.StatusEnum.savings);
        account.setBalance(2000f);
        account.setUser_name("Deepak14");
        account.setIFSC("BANK0005943");

        when(accountService.getByUserName("Deepak14")).thenReturn(account);
        this.mockMvc.perform(get("/api/account/userName/Deepak14"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".account_no").value(100000111))
                .andExpect(MockMvcResultMatchers.jsonPath(".account_type").value("savings"))
                .andExpect(MockMvcResultMatchers.jsonPath(".user_name").value("Deepak14"))
                .andExpect(MockMvcResultMatchers.jsonPath(".balance").value(2000.0))
                .andExpect(MockMvcResultMatchers.jsonPath(".ifsc").value("BANK0005943"))
                .andDo(print());

    }

    @Test
    public void findAllAccountTest() throws Exception {
        Account account1 = new Account();
        account1.setAccount_no(100000111);
        account1.setAccount_type(Account.StatusEnum.savings);
        account1.setBalance(20001f);
        account1.setUser_name("Jayshree14");
        account1.setIFSC("BANK0005943");

        Account account2 = new Account();
        account2.setAccount_no(100000112);
        account2.setAccount_type(Account.StatusEnum.savings);
        account2.setBalance(3000f);
        account2.setUser_name("Deepak123");
        account2.setIFSC("BANK0005943");

        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account1);
        accounts.add(account2);

        when(accountService.getAllAccount()).thenReturn(accounts);
        this.mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void depositBalance() throws Exception {
        Account account = new Account();
        account.setAccount_no(100000111);
        account.setBalance(2000F);
        account.setUser_name("jayshree12");
        account.setAccount_type(Account.StatusEnum.savings);
        account.setIFSC("BANK0001234");
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
                .andDo(print());
    }

}