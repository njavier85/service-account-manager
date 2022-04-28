package com.orion.account.serviceaccountmanager.services;

public interface TransactionService {

    public String generateTransaction(int accountId,double amount, String type);

}
