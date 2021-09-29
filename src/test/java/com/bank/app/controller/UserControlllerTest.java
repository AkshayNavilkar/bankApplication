package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserControlller.class)
public class UserControlllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUserService userService;

    @Test
    public void validateUser() throws Exception {
        User user = new User();
        user.setUserName("SalimAzar");
        user.setUserUid("8665 5331 0291");
        Mockito.when(userService.validateUserByEmail(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

        String url = "/api/userlogin/bc/Dayanand1*";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson =  objectMapper.writeValueAsString(user);
        String actualJson = mvcResult.getResponse().getContentAsString();
        assertNotSame(actualJson,expectedJson);


    }


    @Test
    public void userLogin() throws Exception{
        Account account = new Account();
        account.setBalance((float)500);
        account.setAccountType("savings");
        Mockito.when(userService.validateLogin(Mockito.anyString(),Mockito.anyString())).thenReturn(account);

        String url = "/api/userlogin/bc/Dayanand1*";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson =  objectMapper.writeValueAsString(account);
        String actualJson = mvcResult.getResponse().getContentAsString();
        assertNotSame(actualJson,expectedJson);

    }
}