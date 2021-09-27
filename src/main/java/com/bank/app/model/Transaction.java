package com.bank.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

    @Column(name = "transaction_type")
    @NotNull
    private Boolean transaction_type;

    @Column(name = "account_no")
    @Pattern(regexp = "[0-9]{9,18}")
    @NotBlank(message = "Please provide Account Number")
    private Integer account_no;

    @Column(name = "transaction_amount")
    @NotBlank(message = "Minimum transaction amount should be 1")
    private Float transactionAmount;

    @Column(name = "beneficiary_accno")
    @Pattern(regexp = "[0-9]{9,18}")
    @NotBlank(message = "Please provide Beneficiary Account Number")
    private Integer beneficiaryAccount_no;

    @Column(name = "closing_balance")
    @NotBlank
    private Float closingBalance;

    public Float getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Float closingBalance) {
        this.closingBalance = closingBalance;
    }

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_no", referencedColumnName = "account_no", insertable = false, updatable = false)
    private Account account;

    public Integer getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Integer getAccount_no() {
        return account_no;
    }
    public void setAccount_no(Integer account_no) {
        this.account_no = account_no;
    }

    public Boolean getTransaction_type() {
        return transaction_type;
    }
    public void setTransaction_type(Boolean transaction_type) {
        this.transaction_type = transaction_type;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getBeneficiaryAccount_no() {
        return beneficiaryAccount_no;
    }
    public void setBeneficiaryAccount_no(Integer beneficiaryAccount_no) {
        this.beneficiaryAccount_no = beneficiaryAccount_no;
    }

}
