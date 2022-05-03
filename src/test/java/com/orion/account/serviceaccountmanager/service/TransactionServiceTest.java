package com.orion.account.serviceaccountmanager.service;


import com.orion.account.serviceaccountmanager.client.TransactionClient;
import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.dto.TransactionResponseDTO;
import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.repository.AccountEntityRepository;
import com.orion.account.serviceaccountmanager.services.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TransactionServiceTest {

    @Mock
    private AccountEntityRepository accountRepository;

    @Mock
    private TransactionClient transactionClient;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;


    @Test
    public void generateTransaction_ok_test(){

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(10.0);
        accountEntity.setAccountId(1);

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountEntity));


        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setTransactionId("132-456-789");

        ResponseEntity responseEntity = ResponseEntity.ok(transactionResponseDTO);
        when(transactionClient.createTransaction(any(TransactionRequestDTO.class))).thenReturn(responseEntity);

        Optional<String> response = transactionServiceImpl.generateTransaction(1,10.0,"DEPOSIT");

        Assertions.assertEquals("132-456-789",response.get());
    }


    @Test
    public void generateTransaction_account_not_found_test(){


        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());


        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setTransactionId("132-456-789");

        ResponseEntity responseEntity = ResponseEntity.ok(transactionResponseDTO);
        when(transactionClient.createTransaction(any(TransactionRequestDTO.class))).thenReturn(responseEntity);

        try{
            Optional<String> response = transactionServiceImpl.generateTransaction(1,10.0,"DEPOSIT");
        }catch(AccountException accountException){
            Assertions.assertNotNull(accountException);
        }

    }

}
