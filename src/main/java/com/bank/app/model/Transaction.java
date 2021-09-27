package com.bank.app.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "beneficiary_accno")
    private String beneficiaryAccNo;

    @Column(name = "transaction_amount")
    private String transactionAmount;

    @Column(name = "closing_balance")
    private String closingBalance;

    @ManyToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_no", referencedColumnName = "account_no", insertable = false, updatable = false)
    private Account account;
}
