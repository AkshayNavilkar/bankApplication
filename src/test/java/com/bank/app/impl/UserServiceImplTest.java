package com.bank.app.impl;

import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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

}