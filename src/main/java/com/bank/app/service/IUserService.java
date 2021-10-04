package com.bank.app.service;

import com.bank.app.model.Account;
import com.bank.app.model.User;

import java.util.List;

public interface IUserService {

    public User createUser(User user);
    public User updateUser(String userName, User user);
    public User validateUserByEmail(String otp, String userName);
    public User resendOtp(String userName);
    public List<User> getAllUser();
    public List<User> getAllActiveUser();
    public List<User> getAllInActiveUser();
    public User getUserByUsername(String userName);
    public Account validateLogin(String userName, String password);
}