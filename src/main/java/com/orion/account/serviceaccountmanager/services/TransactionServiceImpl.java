package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.client.TransactionClient;
import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.dto.TransactionResponseDTO;
import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.entity.TransactionEntity;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.exception.TransactionException;
import com.orion.account.serviceaccountmanager.repository.AccountEntityRepository;
import com.orion.account.serviceaccountmanager.utils.TransactionTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService{


    private AccountEntityRepository accountRepository;

    private TransactionClient transactionClient;

    public TransactionServiceImpl(AccountEntityRepository accountRepository, TransactionClient transactionClient) {
        this.accountRepository = accountRepository;
        this.transactionClient = transactionClient;
    }

    public Optional<String> generateTransaction(int accountId, double amount, String type){
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);
        if(optionalAccountEntity.isPresent()){
            AccountEntity accountEntity = optionalAccountEntity.get();
            Optional<String> transactionIdOptional = getTransactionId(accountId,amount,type);
            if(transactionIdOptional.isPresent()){
                double newBalance =0;

                switch (TransactionTypes.valueOf(type)) {
                    case DEPOSIT:
                        newBalance = accountEntity.getBalance()+amount;
                        break;
                    case DEBIT:
                        newBalance = accountEntity.getBalance()-amount;
                        break;
                    default:
                        log.error("m:generateTransaction, Account Type not Recognized:{}",type);
                        throw new AccountException();
                }

                accountEntity.setBalance(newBalance);
                TransactionEntity transactionEntity = new TransactionEntity(transactionIdOptional.get(),accountEntity,amount, LocalDate.now(),type);

                accountEntity.getTransactionEntityEntityList().add(transactionEntity);
                accountRepository.save(accountEntity);
                return transactionIdOptional;
            }else{
                log.error("m:generateTransaction, transaction not processed for accountid:{}",accountId);
            }
        }else{
            log.error("m:generateTransaction, accountid{} not found",accountId);
            throw new AccountException();
        }
        return Optional.empty();
    }

    private Optional<String> getTransactionId(int accountId, double amount, String type) {
        TransactionRequestDTO request = new TransactionRequestDTO(accountId,amount,type);
        String transactionId =null;

        try{
            ResponseEntity<TransactionResponseDTO> responseEntity = transactionClient.createTransaction(request);
            transactionId= responseEntity.getBody().getTransactionId();
            return Optional.of(transactionId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new TransactionException();
        }
    }
}
