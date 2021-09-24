package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserControlller {

    @Autowired
    private IUserService userService;

    @PostMapping("/saveuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
    }
    
    @PutMapping("/user/otp/{otp}/{userId}")
    public ResponseEntity<User> validateUser(@PathVariable("otp") Integer otp,@PathVariable("userId") Integer userId){
    	return new ResponseEntity<User>(userService.validateUserByEmail(otp, userId),HttpStatus.OK);
    }
    
    @GetMapping("/getallUsers")
    public ResponseEntity<List<User>> getAllUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
    }
    
    @GetMapping("/getallactiveusers")
    public ResponseEntity<List<User>> getAllActiveUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllActiveUser(),HttpStatus.OK);
    }

//    @PostMapping("/userlogin/{user_id}/{password}")
//    public @ResponseBody
//    Account userlogin(@PathVariable Integer user_id, @PathVariable String password) throws JsonProcessingException {
//        return userService.validatelogin(user_id, password);
//    }
}
