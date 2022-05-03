package com.orion.account.serviceaccountmanager.controller;


import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.properties.TransactionPropertiesMessages;
import com.orion.account.serviceaccountmanager.services.TransactionService;
import com.orion.account.serviceaccountmanager.utils.TransactionTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionPropertiesMessages transactionPropertiesMessages;

    @InjectMocks
    private TransactionController transactionController;


    @Test
    public void createTransaction_ok_Test(){

        TransactionRequestDTO transactionRequestDTO= new TransactionRequestDTO();
        transactionRequestDTO.setAccountId(1);
        transactionRequestDTO.setAmount(10.0);
        transactionRequestDTO.setType(TransactionTypes.DEPOSIT.name());

        when(transactionService.generateTransaction(anyInt(),anyDouble(),anyString()))
                .thenReturn(Optional.of("123-456-789"));

        ResponseEntity responseEntity = transactionController.createTransaction(transactionRequestDTO);

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        verify(transactionService, times(1)).generateTransaction(anyInt(),anyDouble(),anyString());
    }

    @Test
    public void createTransaction_empty_result_Test(){

        TransactionRequestDTO transactionRequestDTO= new TransactionRequestDTO();
        transactionRequestDTO.setAccountId(1);
        transactionRequestDTO.setAmount(10.0);
        transactionRequestDTO.setType(TransactionTypes.DEPOSIT.name());

        when(transactionService.generateTransaction(anyInt(),anyDouble(),anyString()))
                .thenReturn(Optional.empty());

        ResponseEntity responseEntity = transactionController.createTransaction(transactionRequestDTO);

        Assertions.assertEquals(HttpStatus.CONFLICT,responseEntity.getStatusCode());
        verify(transactionService, times(1)).generateTransaction(anyInt(),anyDouble(),anyString());
    }


    @Test
    public void createTransaction_exception_Test(){

        TransactionRequestDTO transactionRequestDTO= new TransactionRequestDTO();
        transactionRequestDTO.setAccountId(1);
        transactionRequestDTO.setAmount(10.0);
        transactionRequestDTO.setType(TransactionTypes.DEPOSIT.name());

        Mockito.doThrow(new AccountException()).when(transactionService).generateTransaction(anyInt(),anyDouble(),anyString());


        ResponseEntity responseEntity = transactionController.createTransaction(transactionRequestDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        verify(transactionService, times(1)).generateTransaction(anyInt(),anyDouble(),anyString());
    }
}
