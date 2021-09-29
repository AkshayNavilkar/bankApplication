package com.bank.app.impl;

import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;


    @Test
    void createUser() {
        User user = new User();
        user.setUserName("aksNav4528");
        user.setFirstName("aks");
        user.setMiddleName("c");
        user.setLastName("Nav");
        user.setUserEmail("akshay.navilkar@gmail.com");
        user.setAddress("Bangalore");
        user.setUserUid("4163 8161 4672");
        user.setUserPan("BCLPN8608M");
        user.setDateOfBirth("1995-05-01");
        user.setMobileNo("9886676258");
        user.setPassword("Aks123@");
        user.setOtp(null);
        user.setIsActive(true);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        assertThat(userServiceImpl.createUser(user)).isEqualTo(user);
    }

    @Test
    @Order(1)
    public void test_getAllUser() {
        User user = new User();

        user.setUserName("amani");
        user.setFirstName("aam");
        user.setMiddleName("a");
        user.setLastName("ani");
        user.setPassword("amani");
        user.setUserPan("AMANI1234S");
        user.setAddress("warangal");
        user.setDateOfBirth("1998-03-31");
        user.setUserEmail("amani@gmail.com");
        user.setMobileNo("8309115978");
        user.setUserUid("4445 2221 1111");
        user.setOtp("null");
        user.setIsActive(true);

        User user1 = new User();

        user1.setUserName("samantha");
        user1.setFirstName("sam");
        user1.setMiddleName("a");
        user1.setLastName("antha");
        user1.setPassword("samantha");
        user1.setUserPan("SAMAN1234S");
        user1.setAddress("hyderabad");
        user1.setDateOfBirth("1998-03-31");
        user1.setUserEmail("sam@gmail.com");
        user1.setMobileNo("8309115978");
        user1.setUserUid("5555 2221 1111");
        user1.setOtp("null");
        user1.setIsActive(true);


        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        Mockito.when(userRepository.findAll()).thenReturn(users);
        AssertionsForClassTypes.assertThat(userServiceImpl.getAllUser()).isEqualTo(users);
    }

    @Test
    @Order(2)
    public void test_getallactiveusers() {
        User user = new User();


        user.setUserName("amani");
        user.setFirstName("aam");
        user.setMiddleName("a");
        user.setLastName("ani");
        user.setPassword("amani");
        user.setUserPan("AMANI1234S");
        user.setAddress("warangal");
        user.setDateOfBirth("1998-03-31");
        user.setUserEmail("amani@gmail.com");
        user.setMobileNo("8309115978");
        user.setUserUid("4445 2221 1111");
        user.setOtp("null");
        user.setIsActive(true);

        User user1 = new User();

        user1.setUserName("samantha");
        user1.setFirstName("sam");
        user1.setMiddleName("a");
        user1.setLastName("antha");
        user1.setPassword("samantha");
        user1.setUserPan("SAMAN1234S");
        user1.setAddress("hyderabad");
        user1.setDateOfBirth("1998-03-31");
        user1.setUserEmail("sam@gmail.com");
        user1.setMobileNo("8309115978");
        user1.setUserUid("5555 2221 1111");
        user1.setOtp("null");
        user1.setIsActive(true);


        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        Mockito.when(userRepository.getAllActiveUser()).thenReturn(users);
        AssertionsForClassTypes.assertThat(userServiceImpl.getAllActiveUser()).isEqualTo(users);
    }
    @Test
    @Order(3)
    public void test_getAllInActiveUsers() {
        User user = new User();


        user.setUserName("amani");
        user.setFirstName("aam");
        user.setMiddleName("a");
        user.setLastName("ani");
        user.setPassword("amani");
        user.setUserPan("AMANI1234S");
        user.setAddress("warangal");
        user.setDateOfBirth("1998-03-31");
        user.setUserEmail("amani@gmail.com");
        user.setMobileNo("8309115978");
        user.setUserUid("4445 2221 1111");
        user.setOtp("null");
        user.setIsActive(true);

        User user1 = new User();

        user1.setUserName("samantha");
        user1.setFirstName("sam");
        user1.setMiddleName("a");
        user1.setLastName("antha");
        user1.setPassword("samantha");
        user1.setUserPan("SAMAN1234S");
        user1.setAddress("hyderabad");
        user1.setDateOfBirth("1998-03-31");
        user1.setUserEmail("sam@gmail.com");
        user1.setMobileNo("8309115978");
        user1.setUserUid("5555 2221 1111");
        user1.setOtp("null");
        user1.setIsActive(false);


        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        Mockito.when(userRepository.getAllInActiveUser()).thenReturn(users);
        AssertionsForClassTypes.assertThat(userServiceImpl.getAllInActiveUser()).isEqualTo(users);
    }

    @Test
    @Order(1)
    public void test_getUserNameByUser() {
        User user = new User();

        user.setUserName("amani");
        user.setFirstName("aam");
        user.setMiddleName("a");
        user.setLastName("ani");
        user.setPassword("amani");
        user.setUserPan("AMANI1234S");
        user.setAddress("warangal");
        user.setDateOfBirth("1998-03-31");
        user.setUserEmail("amani@gmail.com");
        user.setMobileNo("8309115978");
        user.setUserUid("4445 2221 1111");
        user.setOtp("null");
        user.setIsActive(true);

        Optional<User> opt = Optional.ofNullable(user);

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userRepository.findById(user.getUserName())).thenReturn(opt);

        AssertionsForClassTypes.assertThat(userServiceImpl.getUserByUsername("amani")).isEqualTo(user);
    }

}