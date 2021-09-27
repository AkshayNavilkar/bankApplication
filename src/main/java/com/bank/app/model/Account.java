package com.bank.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", insertable = false, updatable = false)
    private User user;
}
