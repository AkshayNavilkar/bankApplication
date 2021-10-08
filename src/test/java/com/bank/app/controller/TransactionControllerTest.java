package com.bank.app.controller;

import com.bank.app.impl.TransactionServiceImpl;
import com.bank.app.model.Account;
import com.bank.app.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {TransactionControllerTest.class})
public class TransactionControllerTest {
    @Autowired
    MockMvc mockmvc;

    @Mock
    TransactionServiceImpl transactionService;

    @InjectMocks
    TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        mockmvc= MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void getTransactionByAccnoTest() throws Exception
    {
        List<Transaction> transaction = new ArrayList<Transaction>();
        Transaction transaction1 = new Transaction();
        transaction1.setTransaction_id(1);
        transaction1.setAccount_no(100000111);

        Transaction transaction2 = new Transaction();
        transaction2.setTransaction_id(2);
        transaction1.setAccount_no(100000111);

        transaction.add(transaction1);
        transaction.add(transaction2);

        when(transactionService.getTransactionByAccountNo(100000111)).thenReturn(transaction);

        this.mockmvc.perform(get("/api/transaction/accountNumber/100000111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(transaction.size()))
                .andDo(print());
    }
    @Test
    public void addTransactionTest() throws Exception{
        Transaction transaction = new Transaction();
        transaction.setTransaction_id(1);
        transaction.setTransaction_type(Transaction.StatusEnum.debit);
        transaction.setTransactionAmount(150F);
        transaction.setAccount_no(100000111);
        transaction.setBeneficiaryAccount_no(100000112);
        transaction.setClosingBalance((float) 0);
        when(transactionService.addTransaction(transaction)).thenReturn(transaction);

        ObjectMapper mapper = new ObjectMapper();

        String jsonbody=mapper.writeValueAsString(transaction);
        System.out.println(jsonbody);

        this.mockmvc.perform(post("/api/transaction")
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void updateBalanceTest() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransaction_id(1);
        transaction.setAccount_no(100000111);
        transaction.setClosingBalance((float) 1500);
        transaction.setBeneficiaryAccount_no(100000112);
        transaction.setTransactionAmount(100F);
        transaction.setTransaction_type(Transaction.StatusEnum.debit);

        when(transactionService.closingBalance(100000111,1)).thenReturn(transaction);

        ObjectMapper mapper = new ObjectMapper();

        String jsonbody=mapper.writeValueAsString(transaction);
        System.out.println(jsonbody);

        this.mockmvc.perform(patch("/api/transaction/1/100000111")
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}