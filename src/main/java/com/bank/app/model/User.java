package com.bank.app.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "f_name")
    private String f_name;

    @Column(name = "m_name")
    private String m_name;

    @Column(name = "l_name")
    private String l_name;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_pan")
    private String user_pan;

    @Column(name = "user_uid")
    private Long user_uid;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @Column(name = "password")
    private String password;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "isactive")
    private Boolean isactive;
    
    @Column(name = "otp")
    private Integer otp;

    public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
    	Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$");
    	Matcher m = p.matcher(password);
    	if (m.matches())
    		this.password = password;
    	else
    		throw new Exception("Invalid Password !!!");
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) throws Exception{
    	if(EmailValidator.getInstance().isValid(user_email))
            this.user_email = user_email;
        else
            throw new Exception("Enter valid Email ID");
    }

    public String getF_name() {
    	
        return f_name;
    }

    public void setF_name(String f_name) throws Exception {
    	if(StringUtils.isAlpha(f_name))
            this.f_name = f_name;
        else
            throw new Exception("Invalid First name must contain only alphabets.");
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) throws  Exception{
    	if(StringUtils.isAlpha(m_name))
            this.m_name = m_name;
        else
            throw new Exception("Invalid Middle name must contain only alphabets.");
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) throws Exception {
    	if(StringUtils.isAlpha(l_name))
            this.l_name = l_name;
        else
            throw new Exception("Invalid Last name must contain only alphabets.");
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pan() {
        return user_pan;
    }

    public void setUser_pan(String user_pan) {
        this.user_pan = user_pan;
    }

    public Long getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(Long user_uid)throws Exception {
    	Pattern pattern=Pattern.compile("^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$");
    	Matcher matcher = pattern.matcher(String.valueOf(user_uid));
    	if(matcher.matches())
    		this.user_uid = user_uid;
    	else throw new Exception(" Enter Valid User adhar Number ");
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) throws Exception {
    	Pattern pattern=Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    	Matcher matcher = pattern.matcher(date_of_birth);
    	if(matcher.matches())
    	    this.date_of_birth = date_of_birth;
    	else throw new Exception(" Enter Valid DOB ");
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String f_name, String l_name) {
        this.user_name = f_name+l_name;
    }
}
