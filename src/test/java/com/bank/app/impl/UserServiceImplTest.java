package com.bank.app.impl;

import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;

    List<User> listUser = getListUser();

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public List<User> getListUser(){

        List<User> listUser = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("DayanandViveki8998");
        user1.setFirstName("Dayanand");
        user1.setMiddleName("s");
        user1.setLastName("Viveki");
        user1.setUserEmail("dayanandviveki@gmail.com");
        user1.setAddress("Ahmednagar");
        user1.setUserUid("8665 5331 0291");
        user1.setUserPan("ABCDE1234F");
        user1.setDateOfBirth("1997-09-20");
        user1.setMobileNo("7798408988");
        user1.setPassword("Aa@1");
        user1.setOtp("89898");
        user1.setIsActive(false);

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
    @Order(1)
    void test_createUser() {
        when(userRepository.save(listUser.get(0))).thenReturn(listUser.get(0));
        assertThat(userServiceImpl.createUser(listUser.get(0))).isEqualTo(listUser.get(0));
    }
    @Test
    @Order(2)
    void test_updateUser() {
        when(userRepository.findById(listUser.get(0).getUserName())).thenReturn(Optional.ofNullable(listUser.get(0)));
        when(userRepository.save(listUser.get(0))).thenReturn(listUser.get(0));
        assertThat(userServiceImpl.updateUser(listUser.get(0).getUserName(),listUser.get(1)).getUserName()).isEqualTo(listUser.get(0).getUserName());
    }

    @Test
    @Order(3)
    void test_validateUserByEmail() {

        Optional<User> opt = Optional.ofNullable(listUser.get(0));
        when(userRepository.findById(listUser.get(0).getUserName())).thenReturn(opt);
        when(userRepository.getOptValidation(listUser.get(0).getUserName())).thenReturn(opt);
        when(userRepository.validationOtp(listUser.get(0).getUserName(),listUser.get(0).getOtp())).thenReturn(opt);
        when(userRepository.save(opt.get())).thenReturn(opt.get());

        assertThat(userServiceImpl.validateUserByEmail("89898","DayanandViveki8998").getIsActive()).isEqualTo(true);
    }

    @Test
    @Order(4)
    void test_resendOtp() {

        Optional<User> opt = Optional.ofNullable(listUser.get(0));

        when(userRepository.findById(listUser.get(0).getUserName())).thenReturn(opt);
        when(userRepository.getOptValidation(listUser.get(0).getUserName())).thenReturn(opt);
        when(userRepository.save(opt.get())).thenReturn(opt.get());

        assertThat(userServiceImpl.resendOtp(listUser.get(0).getUserName()).getIsActive()).isEqualTo(false);
    }

    @Test
    @Order(5)
    public void test_getAllUser() {
        when(userRepository.findAll()).thenReturn(listUser);
        assertThat(userServiceImpl.getAllUser()).isEqualTo(listUser);
    }

    @Test
    @Order(6)
    public void test_getallactiveusers() {

        when(userRepository.getAllActiveUser()).thenReturn(listUser);
        assertThat(userServiceImpl.getAllActiveUser()).isEqualTo(listUser);
    }

    @Test
    @Order(7)
    public void test_getAllInActiveUsers() {
        when(userRepository.getAllInActiveUser()).thenReturn(listUser);
        AssertionsForClassTypes.assertThat(userServiceImpl.getAllInActiveUser()).isEqualTo(listUser);
    }

    @Test
    @Order(8)
    public void test_getUserNameByUser() {
        Optional opt = Optional.ofNullable(listUser.get(0));
        when(userRepository.findById("DayanandViveki8998")).thenReturn(opt);
        AssertionsForClassTypes.assertThat(userServiceImpl.getUserByUsername("DayanandViveki8998")).isEqualTo(listUser.get(0));
    }

}