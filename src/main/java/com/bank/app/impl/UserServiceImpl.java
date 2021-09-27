package com.bank.app.impl;

import com.bank.app.model.Account;
import com.bank.app.model.User;
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
    public User createUser(User user) {
    	
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
            ex.printStackTrace();
        }

        user.setUser_name(user.getF_name(), user.getL_name());
        user.setIsactive(false);
        user.setOtp(otp);
        return userRepository.save(user);
    }

	@Override
	public User validateUserByEmail(Integer otp, Integer user_Id) {

		User user = userRepository.findById(user_Id).get();
		
		try {
			if(user.getOtp().equals(otp)) {
				user.setIsactive(true);
				user.setOtp(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
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
    public Account validatelogin(Integer user_id, String password) throws JsonProcessingException {
        User found_user = userRepository.getById(user_id);
        Account found_account = accountRepository.getAccountDetailsAfterLogin(user_id);
        Account result = null;
        if(found_user.getUser_id() == user_id && found_user.getPassword().equals(password))
            result = found_account;
        return result;
    }

}
