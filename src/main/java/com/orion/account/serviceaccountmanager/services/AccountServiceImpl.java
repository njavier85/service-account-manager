package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.entity.UserEntity;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.exception.UserException;
import com.orion.account.serviceaccountmanager.properties.AccountControllerPropertiesMessages;
import com.orion.account.serviceaccountmanager.repository.AccountEntityRepository;
import com.orion.account.serviceaccountmanager.repository.UserEntityRepository;
import com.orion.account.serviceaccountmanager.utils.TransactionTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    private AccountEntityRepository accountRepository;
    private TransactionService transactionService;
    private UserEntityRepository userEntityRepository;
    private AccountControllerPropertiesMessages messagesReturnProperties;

    public AccountServiceImpl(AccountEntityRepository accountRepository,UserEntityRepository userEntityRepository,
                              TransactionService transactionService, AccountControllerPropertiesMessages messagesReturnProperties) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.messagesReturnProperties = messagesReturnProperties;
        this.userEntityRepository = userEntityRepository;
    }

    public AccountEntity save(Double credit, Integer userId){

        log.info("m=save, customer_id:{} , credit:{}",userId,credit);

        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(userId);
        if(userEntityOptional.isPresent()){
            AccountEntity accountEntity = new AccountEntity(credit,userEntityOptional.get());
            return accountRepository.save(accountEntity);
        }else{
            log.error(messagesReturnProperties.getCustomernotfound());
            throw new UserException();
        }
    }

    public void createAccount(Integer user_id, Double credit) throws AccountException, UserException {

        log.info("m=createAccount, customer_id:{} , credit:{}",user_id,credit);

        try{
            AccountEntity accountEntity = this.save(0.0,user_id);
            if(credit>0){
                Optional<String> optional = transactionService.generateTransaction(accountEntity.getAccountId(),credit, TransactionTypes.DEPOSIT.name());
                if(optional.isEmpty()){
                    log.error(messagesReturnProperties.getTransactionnotcreated());
                }
            }
        }catch(Exception e){
            log.error(e.getMessage());
            throw new AccountException();
        }
    }
}
