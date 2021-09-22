package com.bank.app.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Integer transaction_id;

    @Column(name = "transaction_type")
    private String transaction_type;

    @Column(name = "account_no")
    private Integer account_no;

    @ManyToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_no", referencedColumnName = "account_no", insertable = false, updatable = false)
    private Account account;

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Integer getAccount_no() {
        return account_no;
    }

    public void setAccount_no(Integer account_no) {
        this.account_no = account_no;
    }
}
