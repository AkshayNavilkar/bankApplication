package com.bank.app.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.regex.Matcher;

@Data
@Entity
@Table(name = "user")
public class User {

    public static final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";
    public static final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String namePattern = "^[A-Za-z]\\w{2,20}$";
    public static final String m_namePattern = "^[A-Za-z]\\w{0,20}$";
    public static final String addrsPattern = "^[a-zA-Z0-9]\\{3,20}$";
    public static final String mobileNoPattern = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
    public static final String uidPattern = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
    public static final String datePattern = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public static final String panPattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    public static final String otpPattern = "^[0-9]{5}$";

    @Id
    @Column(name = "user_name")
    private String userName;

    @NotEmpty
    @Pattern(regexp = passwordPattern, message = "Invalid password, must contain minimum  1 uppercase, 1 lower case & a symbol")
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Pattern(regexp = namePattern, message = "First name is min 3 & max 20 characters, alphabetic")
    @Column(name = "f_name")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = m_namePattern, message = "Middle name min 1 & max 20 characters, alphabetic")
    @Column(name = "m_name")
    private String middleName;

    @NotEmpty
    @Pattern(regexp = namePattern, message = "Last name min 3 & max 20 characters, alphabetic")
    @Column(name = "l_name")
    private String lastName;

    @NotEmpty
    @Pattern(regexp = emailPattern, message = "Invalid email")
    @Column(name = "user_email")
    private String userEmail;

    @NotEmpty
    @Pattern(regexp = panPattern, message = "Inavalid PAN")
    @Column(name = "user_pan")
    private String userPan;

    @NotEmpty
    @Pattern(regexp = datePattern, message = " Date of Birth must be in the format YYYY-MM-DD")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @NotEmpty
    @Pattern(regexp = namePattern, message = "Invalid address")
    @Column(name = "address")
    private String address;

    @NotEmpty
    @Pattern(regexp = mobileNoPattern, message = "Invalid mobile number Plz Enter Valid Mobile Number( 10/12 digit)")
    @Column(name = "mobileno")
    private String mobileNo;

    @NotEmpty
    @Pattern(regexp = uidPattern, message = "Invalid Aadhar Number")
    @Column(name = "user_uid")
    private String userUid;

    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "otp")
    @Pattern(regexp = otpPattern, message = "Invalid Opt It contains only numbers !!!")
    private String otp;
}