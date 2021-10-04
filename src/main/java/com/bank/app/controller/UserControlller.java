package com.bank.app.controller;

import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserControlller {

    @Autowired
    private IUserService userService;

    public final String UPLOAD_DIR = "C:\\Users\\Lenovo\\Downloads\\BankRepo1.1\\BankRepo\\image";

    @PostMapping("/saveuser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        ServletUriComponentsBuilder.fromCurrentRequest();
        System.out.println(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
    	return new ResponseEntity<User>(userService.createUser(user),HttpStatus.OK);
    }

    @PutMapping("/uploadfile/{username}")
    public ResponseEntity<User> uploadFile(@RequestParam("file") MultipartFile multipartFile,@PathVariable("username") String userName) {

       try{
           Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),
                   StandardCopyOption.REPLACE_EXISTING);

       } catch(Exception e){
           e.printStackTrace();
       }
       String filepath = "/BankRepo/image/"+multipartFile.getOriginalFilename();
        return new ResponseEntity<User>(userService.setUserPhoto(userName, filepath),HttpStatus.OK);

    }


    @PutMapping("/updateUser/{username}")
    public ResponseEntity<User> updateUser(@PathVariable("username") String userName, @RequestBody User user){
        ServletUriComponentsBuilder.fromCurrentRequest();
        System.out.println(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return new ResponseEntity<User>(userService.updateUser(userName, user),HttpStatus.OK);
    }

    @PutMapping("/resendOtp/{username}")
    public ResponseEntity<User> resendOtp(@PathVariable("username") String userName){
        return new ResponseEntity<User>(userService.resendOtp(userName),HttpStatus.OK);
    }

    @PutMapping("/user/otp/{otp}/{username}")
    public ResponseEntity<User> validateUser(@PathVariable("otp") String otp,@PathVariable("username") String userName){
    	return new ResponseEntity<User>(userService.validateUserByEmail(otp, userName),HttpStatus.OK);
    }
    
    @GetMapping("/getallUsers")
    public ResponseEntity<List<User>> getAllUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
    }
    
    @GetMapping("/getallactiveusers")
    public ResponseEntity<List<User>> getAllActiveUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllActiveUser(),HttpStatus.OK);
    }

    @GetMapping("/getallinactiveusers")
    public ResponseEntity<List<User>> getAllInActiveUsers(){
    	return new ResponseEntity<List<User>>(userService.getAllInActiveUser(),HttpStatus.OK);
    }

    @GetMapping("/getuserbyusername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String userName){
        return  new ResponseEntity<User>(userService.getUserByUsername(userName),HttpStatus.OK);
    }

    @GetMapping("/userlogin/{username}/{password}")
    public ResponseEntity<Account> userLogin(@PathVariable("username") String userName, @PathVariable("password") String password) {
        return new ResponseEntity<Account>(userService.validateLogin(userName, password),HttpStatus.OK);
    }
}
