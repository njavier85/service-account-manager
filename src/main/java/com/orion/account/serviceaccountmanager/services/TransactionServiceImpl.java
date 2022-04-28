package com.orion.account.serviceaccountmanager.services;

import com.orion.account.serviceaccountmanager.client.TransactionClient;
import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.dto.TransactionResponseDTO;
import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.entity.TransactionEntity;
import com.orion.account.serviceaccountmanager.repository.AccountEntityRepository;
import com.orion.account.serviceaccountmanager.utils.TransactionTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService{


    private AccountEntityRepository accountRepository;

    private TransactionClient transactionClient;

    public TransactionServiceImpl(AccountEntityRepository accountRepository, TransactionClient transactionClient) {
        this.accountRepository = accountRepository;
        this.transactionClient = transactionClient;
    }

    public String generateTransaction(int accountId, double amount, String type){

        TransactionRequestDTO request = new TransactionRequestDTO(accountId,amount,type,"123123");
        String transactionId =null;

        try{
            ResponseEntity<TransactionResponseDTO> responseEntity = transactionClient.createTransaction(request);
            transactionId= responseEntity.getBody().getTransactionId();

        }catch (Exception e){
            e.getMessage();
        }

        AccountEntity accountEntity = accountRepository.findById(accountId).get();
        double newBalance =0;

        if(TransactionTypes.DEPOSIT.name().equals(type)){
             newBalance = accountEntity.getBalance()+amount;
        }else{
             newBalance = accountEntity.getBalance()-amount;
        }
        accountEntity.setBalance(newBalance);
        TransactionEntity transactionEntity = new TransactionEntity(transactionId,accountEntity,amount, LocalDate.now(),type);

        accountEntity.getTransactionEntityEntityList().add(transactionEntity);
        accountRepository.save(accountEntity);
        return transactionId;
    }
}
