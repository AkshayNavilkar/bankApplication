package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(UserControlller.class)
public class UserControlllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IUserService userService;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUserName("aksNav4528");
        user.setFirstName("aks");
        user.setMiddleName("c");
        user.setLastName("Nav");
        user.setUserEmail("akshay.navilkar@gmail.com");
        user.setAddress("Belgaum");
        user.setUserUid("4163 8161 4672");
        user.setUserPan("BCLPN8608M");
        user.setDateOfBirth("1995-05-01");
        user.setMobileNo("9886676258");
        user.setPassword("Aks123@");
        user.setOtp(null);
        user.setIsActive(true);
        System.out.println(user);
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        String url = "http://localhost:8080/api/saveuser";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        User inputJson = new ObjectMapper().readValue(inputToJson, User.class);
        User outputJson = new ObjectMapper().readValue(outputToJson, User.class);
        assertNotSame(outputJson.getUserName(), inputJson.getUserName());
        assertNotSame(outputJson.getPassword(), inputJson.getPassword());
        if (!EmailValidator.getInstance().isValid(inputJson.getUserEmail()))
            throw new Exception("Invalid Email");
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void test_getAllUser() throws Exception {
        User user = new User();
        user.setUserName("sripada");
        user.setFirstName("sri");
        user.setMiddleName("i");
        user.setLastName("pada");
        user.setUserEmail("sripada@gmail.com");
        user.setAddress("warangal");
        user.setUserUid("4163 8161 4672");
        user.setUserPan("BCLPN8608M");
        user.setDateOfBirth("1995-05-01");
        user.setMobileNo("9886676258");
        user.setPassword("Am123@");
        user.setOtp(null);
        user.setIsActive(true);
        System.out.println(user);
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        String url = "http://localhost:8080/api/saveuser";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        User inputJson = new ObjectMapper().readValue(inputToJson, User.class);
        User outputJson = new ObjectMapper().readValue(outputToJson, User.class);
        assertNotSame(outputJson.getUserName(), inputJson.getUserName());
        assertNotSame(outputJson.getPassword(), inputJson.getPassword());
        if (!EmailValidator.getInstance().isValid(inputJson.getUserEmail()))
            throw new Exception("Invalid Email");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    @Test
    public void test_getAllActiveUsers() throws Exception {
        User user = new User();
        user.setUserName("sripada");
        user.setFirstName("sri");
        user.setMiddleName("i");
        user.setLastName("pada");
        user.setUserEmail("sripada@gmail.com");
        user.setAddress("warangal");
        user.setUserUid("4163 8161 4672");
        user.setUserPan("BCLPN8608M");
        user.setDateOfBirth("1995-05-01");
        user.setMobileNo("9886676258");
        user.setPassword("Am123@");
        user.setOtp(null);
        user.setIsActive(true);
        System.out.println(user);
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        String url = "http://localhost:8080/api/saveuser";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        User inputJson = new ObjectMapper().readValue(inputToJson, User.class);
        User outputJson = new ObjectMapper().readValue(outputToJson, User.class);
        assertNotSame(outputJson.getUserName(),inputJson.getUserName());
        assertNotSame(outputJson.getPassword(), inputJson.getPassword());
        if(!EmailValidator.getInstance().isValid(inputJson.getUserEmail()))
            throw new Exception("Invalid Email");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void test_getAllInActiveUsers() throws Exception {
        User user = new User();
        user.setUserName("sripada");
        user.setFirstName("sri");
        user.setMiddleName("i");
        user.setLastName("pada");
        user.setUserEmail("sripada@gmail.com");
        user.setAddress("warangal");
        user.setUserUid("4163 8161 4672");
        user.setUserPan("BCLPN8608M");
        user.setDateOfBirth("1995-05-01");
        user.setMobileNo("9886676258");
        user.setPassword("Am123@");
        user.setOtp(null);
        user.setIsActive(true);
        System.out.println(user);
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        String url = "http://localhost:8080/api/saveuser";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        User inputJson = new ObjectMapper().readValue(inputToJson, User.class);
        User outputJson = new ObjectMapper().readValue(outputToJson, User.class);
        assertNotSame(outputJson.getUserName(),inputJson.getUserName());
        assertNotSame(outputJson.getPassword(), inputJson.getPassword());
        if(!EmailValidator.getInstance().isValid(inputJson.getUserEmail()))
            throw new Exception("Invalid Email");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    @Test
    public void test_getUserByUserName() throws Exception {
        User user = new User();
        user.setUserName("sripada");
        user.setFirstName("sri");
        user.setMiddleName("i");
        user.setLastName("pada");
        user.setUserEmail("sripada@gmail.com");
        user.setAddress("warangal");
        user.setUserUid("4163 8161 4672");
        user.setUserPan("BCLPN8608M");
        user.setDateOfBirth("1995-05-01");
        user.setMobileNo("9886676258");
        user.setPassword("Am123@");
        user.setOtp(null);
        user.setIsActive(true);
        System.out.println(user);
        String inputToJson = this.mapToJson(user);
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        String url = "http://localhost:8080/api/saveuser";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url).accept(MediaType.APPLICATION_JSON).content(inputToJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String outputToJson = response.getContentAsString();
        User inputJson = new ObjectMapper().readValue(inputToJson, User.class);
        User outputJson = new ObjectMapper().readValue(outputToJson, User.class);
        assertNotSame(outputJson.getUserName(),inputJson.getUserName());
        assertNotSame(outputJson.getPassword(), inputJson.getPassword());
        if(!EmailValidator.getInstance().isValid(inputJson.getUserEmail()))
            throw new Exception("Invalid Email");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

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

