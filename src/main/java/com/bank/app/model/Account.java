package com.bank.app.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")
    @Pattern(regexp = "[0-9]{9,18}")
    private Integer account_no;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private StatusEnum account_type;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "balance")
    @DecimalMin(value = "1000",message = "")
    private Float balance;

    @Column(name = "ifsc_code")
//    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$")
    private String IFSC;

    public enum StatusEnum {
        savings, current
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    public Integer getAccount_no() {
        return account_no;
    }

    public void setAccount_no(Integer account_no) {
        this.account_no = account_no;
    }

    public StatusEnum getAccount_type() {
        return account_type;
    }

    public void setAccount_type(StatusEnum account_type) {
        this.account_type = account_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }
}
