package com.orion.account.serviceaccountmanager.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AccountRequestDTO {

    private Integer customerId;

    private Double credit;

    private Integer accountId;

    private List<TransactionRequestDTO> transactionRequestDTOList = new ArrayList<TransactionRequestDTO>();

    public AccountRequestDTO(Integer accountId,Integer customerId, Double credit,List<TransactionRequestDTO> transactionRequestDTOList) {
        this.customerId = customerId;
        this.credit = credit;
        this.accountId = accountId;
        this.transactionRequestDTOList =transactionRequestDTOList;
    }

    public List<TransactionRequestDTO> getTransactionRequestDTOList() {
        return transactionRequestDTOList;
    }

    public void setTransactionRequestDTOList(List<TransactionRequestDTO> transactionRequestDTOList) {
        this.transactionRequestDTOList = transactionRequestDTOList;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
