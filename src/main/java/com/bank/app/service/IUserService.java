package com.bank.app.service;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IUserService {
	
    User createUser(User user);
    User validateUserByEmail(Integer otp, String user_name);
    List<User> getAllUser();
    List<User> getAllActiveUser();
    Account validatelogin(String user_name, String password) throws JsonProcessingException;
}
