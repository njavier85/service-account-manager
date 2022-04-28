package com.orion.account.serviceaccountmanager.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class TransactionEntity {

    @Id
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @Column
    private double amount;

    @Column
    private LocalDate date;

    @Column
    private String type;

    public TransactionEntity() {

    }


    public TransactionEntity(String transactionId, AccountEntity accountEntity, double amount, LocalDate date,String type) {
        this.transactionId = transactionId;
        this.accountEntity = accountEntity;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }



    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
