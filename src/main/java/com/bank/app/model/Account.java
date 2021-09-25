package com.bank.app.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")
    private Integer account_no;

    @Column(name = "account_type")
    private String account_type;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "balance")
    private Float balance;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", insertable = false, updatable = false)
    private User user;

    public Integer getAccount_no() {
        return account_no;
    }

    public void setAccount_no(Integer account_no) {
        this.account_no = account_no;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
