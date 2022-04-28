package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.entity.TransactionEntity;
import com.orion.account.serviceaccountmanager.entity.UserEntity;
import com.orion.account.serviceaccountmanager.repository.AccountEntityRepository;
import com.orion.account.serviceaccountmanager.repository.UserEntityRepository;
import com.orion.account.serviceaccountmanager.utils.TransactionTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountEntityRepository accountRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private TransactionService transactionService;
    public void createAccount(int user_id, double credit){

        UserEntity userEntity = userEntityRepository.findById(user_id).get();

        AccountEntity accountEntity = new AccountEntity(credit,userEntity);

        if(credit>0){
            UUID uuid = UUID.randomUUID();
            TransactionEntity transactionEntity = new TransactionEntity(uuid.toString(),accountEntity,credit, LocalDate.now(), TransactionTypes.DEPOSIT.name());
            accountEntity.getTransactionEntityEntityList().add(transactionEntity);
        }

        accountRepository.save(accountEntity);

    }
}
