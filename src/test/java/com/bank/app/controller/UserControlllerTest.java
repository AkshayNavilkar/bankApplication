package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControlller.class)
public class UserControlllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IUserService userService;

    List<User> listUser = getListUser();

    private String mapToJson(Object object) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public List<User> getListUser(){

        List<User> listUser = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("aksNav4528");
        user1.setFirstName("aks");
        user1.setMiddleName("c");
        user1.setLastName("Nav");
        user1.setUserEmail("akshay.navilkar@gmail.com");
        user1.setAddress("Belgaum");
        user1.setUserUid("4163 8161 4672");
        user1.setUserPan("BCLPN8608M");
        user1.setDateOfBirth("1995-05-01");
        user1.setMobileNo("9886676258");
        user1.setPassword("Aks123@");
        user1.setOtp(null);
        user1.setIsActive(true);

        User user2 = new User();
        user2.setUserName("sripada");
        user2.setFirstName("sri");
        user2.setMiddleName("i");
        user2.setLastName("pada");
        user2.setUserEmail("sripada@gmail.com");
        user2.setAddress("warangal");
        user2.setUserUid("4163 8161 4672");
        user2.setUserPan("BCLPN8608M");
        user2.setDateOfBirth("1995-05-01");
        user2.setMobileNo("9886676258");
        user2.setPassword("Am123@");
        user2.setOtp(null);
        user2.setIsActive(true);

        User user3 = new User();
        user3.setUserName("sripada");
        user3.setFirstName("sri");
        user3.setMiddleName("i");
        user3.setLastName("pada");
        user3.setUserEmail("sripada@gmail.com");
        user3.setAddress("warangal");
        user3.setUserUid("4163 8161 4672");
        user3.setUserPan("BCLPN8608M");
        user3.setDateOfBirth("1995-05-01");
        user3.setMobileNo("9886676258");
        user3.setPassword("Am123@");
        user3.setOtp(null);
        user3.setIsActive(true);

        User user4 = new User();
        user4.setUserName("sripada");
        user4.setFirstName("sri");
        user4.setMiddleName("i");
        user4.setLastName("pada");
        user4.setUserEmail("sripada@gmail.com");
        user4.setAddress("warangal");
        user4.setUserUid("4163 8161 4672");
        user4.setUserPan("BCLPN8608M");
        user4.setDateOfBirth("1995-05-01");
        user4.setMobileNo("9886676258");
        user4.setPassword("Am123@");
        user4.setOtp(null);
        user4.setIsActive(true);

        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
        listUser.add(user4);

        return listUser;
    }

    @Test
    public void test_createUser() throws Exception {
        when(userService.createUser(Mockito.any(User.class))).thenReturn(listUser.get(0));
        mockMvc.perform(post("/api/saveuser")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(listUser.get(0)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_updateUser() throws Exception {
        when(userService.updateUser(listUser.get(0).getUserName(),listUser.get(1))).thenReturn(listUser.get(0));
        mockMvc.perform(put("/api/updateUser/daya")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapToJson(listUser.get(0)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void test_getAllInActiveUsers() throws Exception {

        when(userService.getAllInActiveUser()).thenReturn(listUser);

        mockMvc.perform(get("/api/getallinactiveusers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getUserByUserName() throws Exception {

        when(userService.getUserByUsername(listUser.get(0).getUserName())).thenReturn(listUser.get(0));

        mockMvc.perform(get("/api/getuserbyusername/dayanand")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
