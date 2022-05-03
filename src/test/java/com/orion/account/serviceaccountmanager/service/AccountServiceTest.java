package com.orion.account.serviceaccountmanager.service;

import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.entity.UserEntity;
import com.orion.account.serviceaccountmanager.exception.UserException;
import com.orion.account.serviceaccountmanager.properties.AccountControllerPropertiesMessages;
import com.orion.account.serviceaccountmanager.repository.AccountEntityRepository;
import com.orion.account.serviceaccountmanager.repository.UserEntityRepository;
import com.orion.account.serviceaccountmanager.services.AccountServiceImpl;
import com.orion.account.serviceaccountmanager.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountEntityRepository accountRepository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private AccountControllerPropertiesMessages messagesReturnProperties;

    @InjectMocks
    private AccountServiceImpl accountService;


    @Test
    public void save_ok_Test(){

        UserEntity userEntity = new UserEntity();

        when(userEntityRepository.findById(anyInt()))
                .thenReturn(Optional.of(userEntity));


        AccountEntity accountEntity = new AccountEntity();
        when(accountRepository.save(any(AccountEntity.class)))
                .thenReturn(accountEntity);

        Assertions.assertNotNull(accountService.save(10.0,1));

    }

    @Test
    public void save_null_Test(){

        when(userEntityRepository.findById(anyInt())).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, ()->{accountService.save(10.0,1);} );

        Assertions.assertNotNull(exception);
    }


    @Test
    public void createAccount_ok_Test(){

        UserEntity userEntity = new UserEntity();

        when(userEntityRepository.findById(anyInt()))
                .thenReturn(Optional.of(userEntity));


        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(1);
        accountEntity.setBalance(10);

        when(accountRepository.save(any(AccountEntity.class)))
                .thenReturn(accountEntity);

        when(transactionService.generateTransaction(anyInt(),anyDouble(),anyString()))
                .thenReturn(Optional.of("123-456-789"));

        accountService.createAccount(1,10.0);
    }

    @Test
    public void createAccount_ok_credit_0_Test(){

        UserEntity userEntity = new UserEntity();

        when(userEntityRepository.findById(anyInt()))
                .thenReturn(Optional.of(userEntity));


        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(1);
        accountEntity.setBalance(10);

        when(accountRepository.save(any(AccountEntity.class)))
                .thenReturn(accountEntity);

        when(transactionService.generateTransaction(anyInt(),anyDouble(),anyString()))
                .thenReturn(Optional.of("123-456-789"));

        accountService.createAccount(1,0.0);
    }
}
