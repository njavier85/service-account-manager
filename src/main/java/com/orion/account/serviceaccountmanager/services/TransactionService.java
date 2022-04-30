package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.utils.TransactionTypes;

import java.util.Optional;

public interface TransactionService {

    public Optional<String> generateTransaction(int accountId, double amount, String type);

}
