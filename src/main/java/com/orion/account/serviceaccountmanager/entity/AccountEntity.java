package com.orion.account.serviceaccountmanager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Integer accountId;

    public List<TransactionEntity> getTransactionEntityEntityList() {
        return transactionEntityEntityList;
    }

    public void setTransactionEntityEntityList(List<TransactionEntity> transactionEntityEntityList) {
        this.transactionEntityEntityList = transactionEntityEntityList;
    }

    @OneToMany(
            mappedBy = "accountEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransactionEntity> transactionEntityEntityList = new ArrayList<TransactionEntity>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;



    @Column
    private double balance;

    public AccountEntity() {

    }

    public AccountEntity(double balance, UserEntity userEntity) {
        this.userEntity = userEntity;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
