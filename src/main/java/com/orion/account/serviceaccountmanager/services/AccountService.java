package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.exception.AccountException;

public interface AccountService {

    public void createAccount(Integer user_id, Double credit) throws AccountException;

    public AccountEntity save(Double credit, Integer userId);
}
