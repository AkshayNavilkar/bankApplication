package com.bank.app.service;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public User saveImage(String username, MultipartFile file) throws IOException;
    public void getImage(String username, HttpServletResponse response) throws IOException;


}