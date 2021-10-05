package com.bank.app.impl;

import com.bank.app.exception.MultiPartFileUploadException;
import com.bank.app.exception.SendingFailedException;
import com.bank.app.exception.ValidationFailedException;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.UserRepository;
import com.bank.app.service.IUserService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.jetbrains.annotations.NotNull;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    static Integer otp;

    public void sendOtp(@NotNull User user){

        Email from = new Email("akshay2navilkar@gmail.com");
        String subject = "Email OTP Verification";
        Email to = new Email(user.getUserEmail());
        Request request = new Request();
        otp = new Random().nextInt(90000) + 10000;

        Content content = new Content("text/plain", "Hello "+user.getFirstName()+" "+ user.getLastName()+" "+otp+" is your one time password and "
                + "is valid for next 15 minutes. Please enter the same to complete your Email validation. Regards NoManey Bank.");

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new SendingFailedException("Failed to send OTP");
        }
    }

    @Override
    public User createUser(User user){
        User newUser = null;

        try{
            sendOtp(user);
            String usernName = user.getFirstName()+user.getLastName()+new Random().nextInt(9999);
            user.setUserName(usernName);
            user.setActive(false);
            user.setOtp(String.valueOf(otp));
            newUser =  userRepository.save(user);
        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }catch(NoSuchElementException nsee){
            throw new ValidationFailedException("Invalid Username !!!");
        }catch(TransactionSystemException tse){
            throw new ValidationFailedException("Database getting Error !!!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return  newUser;
    }

    @Override
    public User updateUser(String userName, User user) {
        User oldUser= null;
        try{
            oldUser = userRepository.findById(userName).get();
            if(user.getFirstName() != null)
                oldUser.setFirstName(user.getFirstName());
            if(user.getMiddleName() != null)
                oldUser.setMiddleName(user.getMiddleName());
            if(user.getLastName() != null)
                oldUser.setLastName(user.getLastName());
            if(user.getFirstName() != null)
                oldUser.setFirstName(user.getFirstName());
            if(user.getUserEmail() != null)
                oldUser.setUserEmail(user.getUserEmail());
            if(user.getUserPan() != null)
                oldUser.setUserPan(user.getUserPan());
            if(user.getDateOfBirth() != null)
                oldUser.setDateOfBirth(user.getDateOfBirth());
            if(user.getAddress() != null)
                oldUser.setAddress(user.getAddress());
            if(user.getMobileNo() != null)
                oldUser.setMobileNo(user.getMobileNo());
            if(user.getUserUid() != null)
                oldUser.setUserUid(user.getUserUid());
            if(user.getPassword() != null)
                oldUser.setPassword(user.getPassword());

            sendOtp(oldUser);
            oldUser.setActive(false);
            oldUser.setOtp(String.valueOf(otp));
            oldUser = userRepository.save(oldUser);
        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }catch(NoSuchElementException nsee){
            throw new ValidationFailedException("Invalid Username !!!");
        }catch(TransactionSystemException tse){
            throw new ValidationFailedException("Database getting Error !!!");
        }catch(HttpMessageNotReadableException hmnqe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return oldUser;
    }

    @Override
    public User validateUserByEmail(String otp, String userName) {

        User user = null;
        String msg = null;
        try {
            msg = "Invalid Username plz Enter Valid Username!!!";
            user = userRepository.findById(userName).get();

            msg = "Otp Validation Already Done By User ...";
            user = userRepository.getOptValidation(userName).get();

            msg = "Invalid OTP !!!";
            user = userRepository.validationOtp(userName, otp).get();

            user.setOtp(null);
            user.setActive(true);

            user = userRepository.save(user);

        }catch(NullPointerException npe) {
            throw new ValidationFailedException("Invalid Username !!!");
        }catch(NoSuchElementException nsee) {
            throw new ValidationFailedException(msg);
        }catch(Exception e) {
            e.printStackTrace();
            throw new ValidationFailedException(msg);
        }
        return user;
    }

    @Override
    public User resendOtp(String userName)  {
        User user= null ;
        String msg = null;
        try{
            msg = "Invalid Username !!!";
            user = userRepository.findById(userName).get();

            msg = "Otp Validation Already done By User ...";
            user = userRepository.getOptValidation(userName).get();

            sendOtp(user);
            user.setOtp(String.valueOf(otp));
            user =  userRepository.save(user);

        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }catch(NoSuchElementException nsee){
            throw new ValidationFailedException(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllActiveUser() {
        return userRepository.getAllActiveUser();
    }

    @Override
    public List<User> getAllInActiveUser() {
        return userRepository.getAllInActiveUser();
    }

    @Override
    public User getUserByUsername(String userName) {
        User user = null;
        try{
            user = userRepository.findById(userName).get();
        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        }catch(NoSuchElementException nsee){
            throw new ValidationFailedException("Invalid Username !!!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Account validateLogin(String userName, String password) {

        Account findAccount = null;
        User validatedUser = null;
        String msg = null;
        try{
            msg = "Invalid Username !!!";
            validatedUser = userRepository.findById(userName).get();

            msg = "Invalid Password !!!";
            validatedUser = userRepository.getByUserNameAndPassword(userName,password).get();

            msg ="Otp Validation not done By User";
            validatedUser = userRepository.isActive(userName, password).get();

            msg = "Account Not Created by User ...";
            findAccount = accountRepository.getAccountDetailsAfterLogin(userName).get();

        }catch(NullPointerException npe){
            throw new ValidationFailedException("Invalid User Details !!!");
        } catch(Exception nsee){
            throw new ValidationFailedException(msg);
        }
        return findAccount;
    }

    @Override
    public User saveImage(String username , MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        User user = userRepository.findById(username).get();
        if(!file.isEmpty()) {
            user.setImage(file.getBytes());
            return userRepository.save(user);
        } else {
            return null;
        }

    }

    public void getImage(String username, HttpServletResponse response) throws IOException {
        Optional<User> user = userRepository.findById(username);
        if(user.isPresent()) {
            response.getOutputStream().write(user.get().getImage());
            response.getOutputStream().close();
        } else {
            System.out.println("User is not active");
        }
    }




}