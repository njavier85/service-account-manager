package com.orion.account.serviceaccountmanager.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.orion.account.serviceaccountmanager.utils.TransactionTypes;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TransactionRequestDTO {

    private Integer accountId;

    private String transactionId;

    private Double amount;

    private String type;

    public TransactionRequestDTO(Integer accountId, Double amount, String type) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public TransactionRequestDTO(Integer accountId, Double amount, String type, String transactionId) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.transactionId = transactionId;
    }

    public TransactionRequestDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
