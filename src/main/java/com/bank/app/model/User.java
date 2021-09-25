package com.bank.app.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.regex.Matcher;

@Entity
@Table(name = "user")
public class User {

    public static final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";
    public static final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String namePattern = "^[A-Za-z]\\w{2,20}$";
    public static final String m_namePattern = "^[A-Za-z]\\w{0,20}$";
    public static final String addrsPattern = "^[a-zA-Z0-9]\\{3,20}$";
   // public static final String mobileNoPattern = "(0/91)?[7-9][0-9]{9}";
    public static final String mobileNoPattern = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
    public static final String uidPattern = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
    public static final String datePattern = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public static final String panPattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";


    @Id
    @Column(name = "user_name")
    private String user_name;

    @NotEmpty
    @Pattern(regexp = namePattern, message = "f_name - min 3 & max 20 characters, alphabetic")
    @Column(name = "f_name")
    private String f_name;

    @NotEmpty
    @Pattern(regexp = m_namePattern, message = "m_name - min 1 & max 20 characters, alphabetic")
    @Column(name = "m_name")
    private String m_name;

    @NotEmpty
    @Pattern(regexp = namePattern, message = "l_name - min 3 & max 20 characters, alphabetic")
    @Column(name = "l_name")
    private String l_name;

    @NotEmpty
    @Pattern(regexp = panPattern, message = "inavalid PAN")
    @Column(name = "user_pan")
    private String user_pan;

    @NotEmpty
    @Pattern(regexp = uidPattern, message = "invalid Aadhar Number")
    @Column(name = "user_uid")
    private String user_uid;

    @NotEmpty
    @Pattern(regexp = datePattern, message = "date_of_birth must be in the format YYYY-MM-DD")
    @Column(name = "date_of_birth")
    private String date_of_birth;

    @NotEmpty
    @Pattern(regexp = passwordPattern, message = "invalid password, must contain minimum  1 uppercase, 1 lower case & a symbol")
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Pattern(regexp = emailPattern, message = "invalid email")
    @Column(name = "user_email")
    private String user_email;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "otp")
    private Integer otp;

    @NotEmpty
    @Pattern(regexp = namePattern, message = "invalid address")
    @Column(name = "address")
    private String address;

    @NotEmpty
    @Pattern(regexp = mobileNoPattern, message = "Enter valid 12 digit mobile number")
    @Column(name = "mobileno")
    private String mobileno;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

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

    public void setPassword(String password){
    		this.password = password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email){
            this.user_email = user_email;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name){
        this.f_name = f_name;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name){
            this.m_name = m_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name){
            this.l_name = l_name;
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

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid){
    		this.user_uid = user_uid;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth){
    	    this.date_of_birth = date_of_birth;
    }

    public void setUser_name(String f_name, String l_name) {
        this.user_name = f_name+l_name;
    }
}
