package com.bank.app.service;

import com.bank.app.model.User;

import java.util.List;

public interface IUserService {
	
    User createUser(User user);
    User validateUserByEmail(Integer otp, Integer user_Id);
    List<User> getAllUser();
    List<User> getAllActiveUser();    
}
