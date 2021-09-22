package com.bank.app.impl;

import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import com.bank.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setF_name(user.getF_name());
        user.setM_name(user.getM_name());
        user.setL_name(user.getL_name());
        user.setDate_of_birth(user.getDate_of_birth());
        user.setUser_pan(user.getUser_pan());
        user.setUser_uid(user.getUser_uid());
        user.setUser_name(user.getF_name(), user.getL_name());
        return userRepository.save(user);
    }
}
