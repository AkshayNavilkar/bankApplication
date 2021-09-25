package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserControlller {

    @Autowired
    private IUserService userService;

    @PostMapping("/saveuser")
    public @ResponseBody User createUser(@Valid @RequestBody User user) {
    	return userService.createUser(user);
    }
    
    @PutMapping("/user/otp/{otp}/{user_name}")
    public ResponseEntity<User> validateUser(@PathVariable("otp") Integer otp,@PathVariable("user_name") String user_name){
    	return new ResponseEntity<User>(userService.validateUserByEmail(otp, user_name),HttpStatus.OK);
    }
    
    @GetMapping("/getallUsers")
    public ResponseEntity<List<User>> getAllUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
    }
    
    @GetMapping("/getallactiveusers")
    public ResponseEntity<List<User>> getAllActiveUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllActiveUser(),HttpStatus.OK);
    }

    @PostMapping("/userlogin/{user_name}/{password}")
    public @ResponseBody
    Account userlogin(@PathVariable String user_name, @PathVariable String password) throws JsonProcessingException {
        return userService.validatelogin(user_name, password);
    }
}
