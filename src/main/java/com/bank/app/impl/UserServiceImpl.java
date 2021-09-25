package com.bank.app.impl;

import com.bank.app.exception.SendingFailedException;
import com.bank.app.exception.ValidationFailedException;
import com.bank.app.model.Account;
import com.bank.app.model.User;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.UserRepository;
import com.bank.app.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public User createUser(User user){
    	
        Email from = new Email("akshay2navilkar@gmail.com");
        String subject = "Email OTP Verification";
        Email to = new Email(user.getUser_email());
        Request request = new Request();
        Integer otp = new Random().nextInt(90000) + 10000;
 
        Content content = new Content("text/plain", "Hello "+user.getF_name()+" "+otp+" is your one time password and "
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
        user.setF_name(user.getF_name());
        user.setM_name(user.getM_name());
        user.setL_name(user.getL_name());
        user.setUser_pan(user.getUser_pan());
        user.setPassword(user.getPassword());
        user.setUser_uid(user.getUser_uid());
        user.setUser_email(user.getUser_email());
        user.setUser_name(user.getF_name(), user.getL_name());
        user.setAddress(user.getAddress());
        user.setMobileno(user.getMobileno());
        user.setDate_of_birth(user.getDate_of_birth());
        user.setIsactive(false);
        user.setOtp(otp);
        return userRepository.save(user);
    }

	@Override
	public User validateUserByEmail(Integer otp, String user_name) {

        User user = new User();
		try {
            user = userRepository.getByUserName(user_name);
            if(user.getOtp().equals(otp)) {
                    user.setIsactive(true);
                    user.setOtp(0);
                }
		}catch(Exception e) {
			throw new ValidationFailedException("Enter Valid OTP or user_name");
		}
		return	userRepository.save(user);
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
    public Account validatelogin(String user_name, String password) throws ValidationFailedException{
        Account result = null;
        try {
            User found_user = userRepository.getByUserName(user_name);
            if(found_user.getPassword().equals(password)) {
                Account found_account = accountRepository.getAccountDetailsAfterLogin(user_name);

                if (found_user.getUser_name().equals(user_name) && found_user.getPassword().equals(password))
                    result = found_account;
            }
            else
                throw new ValidationFailedException("Enter Valid user_name or password");

        }
        catch (Exception e){
            throw new ValidationFailedException("Enter Valid user_name or password");
        }
        return result;
    }

}
