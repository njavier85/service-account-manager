package com.orion.account.serviceaccountmanager.controller;


import com.orion.account.serviceaccountmanager.dto.AccountRequestDTO;
import com.orion.account.serviceaccountmanager.dto.ErrorResponseDTO;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.exception.UserException;
import com.orion.account.serviceaccountmanager.properties.AccountControllerPropertiesMessages;
import com.orion.account.serviceaccountmanager.services.AccountService;
import com.orion.account.serviceaccountmanager.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountServiceController {

    private UserService userService;
    private AccountService accountService;
    private AccountControllerPropertiesMessages messagesReturnProperties;

    public AccountServiceController(UserService userService, AccountService accountService, AccountControllerPropertiesMessages messagesReturnProperties) {
        this.userService = userService;
        this.accountService = accountService;
        this.messagesReturnProperties = messagesReturnProperties;
    }

    @PostMapping("/create-account")
    public ResponseEntity createNewAccount(@RequestBody AccountRequestDTO accountRequestDTO) {

        log.info("m=createNewAccount, customer_id:{} , credit:{}",accountRequestDTO.getCustomerId(),accountRequestDTO.getCredit());

        if(!validAccountRequest(accountRequestDTO)){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(messagesReturnProperties.getMessageparamtererror());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

        try{
            accountService.createAccount(accountRequestDTO.getCustomerId(),accountRequestDTO.getCredit());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (AccountException e){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(messagesReturnProperties.getAccountnotcreated());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
        }catch (UserException ue){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(messagesReturnProperties.getAccountnotcreated());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }
    }

    private boolean validAccountRequest(AccountRequestDTO accountRequestDTO) {
        if(accountRequestDTO.getCredit()!=null && accountRequestDTO.getCustomerId()!=null && accountRequestDTO.getCredit()>=0){
            return true;
        }
        return false;
    }
}
