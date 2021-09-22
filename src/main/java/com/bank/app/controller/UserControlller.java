package com.bank.app.controller;

import com.bank.app.model.User;
import com.bank.app.repository.UserRepository;
import com.bank.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserControlller {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("user")
    public @ResponseBody User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }
}
