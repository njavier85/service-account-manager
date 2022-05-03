package com.orion.account.serviceaccountmanager.controller;


import com.orion.account.serviceaccountmanager.dto.AccountRequestDTO;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.exception.UserException;
import com.orion.account.serviceaccountmanager.properties.AccountControllerPropertiesMessages;
import com.orion.account.serviceaccountmanager.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountControllerTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private AccountControllerPropertiesMessages messagesReturnProperties;

    @Test
    public void createAccount_ok_Test(){
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1,10.0);

        ResponseEntity responseEntity = accountController.createNewAccount(accountRequestDTO);

        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        verify(accountService, times(1)).createAccount(anyInt(),anyDouble());
    }

    @Test
    public void createAccount_null_values_Test(){
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(null,null);

        ResponseEntity responseEntity = accountController.createNewAccount(accountRequestDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

    @Test
    public void createAccount_amount_negative_Test(){
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1,-30.0);

        ResponseEntity responseEntity = accountController.createNewAccount(accountRequestDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

    @Test
    public void createAccount_user_not_found_Test(){
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1,30.0);

        Mockito.doThrow(new UserException()).when(accountService).createAccount(anyInt(),anyDouble());

        ResponseEntity responseEntity = accountController.createNewAccount(accountRequestDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }


    @Test
    public void createAccount_account_db_issue_Test(){
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO(1,30.0);

        Mockito.doThrow(new AccountException()).when(accountService).createAccount(anyInt(),anyDouble());

        ResponseEntity responseEntity = accountController.createNewAccount(accountRequestDTO);

        Assertions.assertEquals(HttpStatus.CONFLICT,responseEntity.getStatusCode());
    }
}
