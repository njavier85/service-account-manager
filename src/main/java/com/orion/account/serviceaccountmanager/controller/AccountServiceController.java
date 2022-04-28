package com.orion.account.serviceaccountmanager.controller;


import com.orion.account.serviceaccountmanager.dto.AccountRequestDTO;
import com.orion.account.serviceaccountmanager.dto.ErrorResponseDTO;
import com.orion.account.serviceaccountmanager.dto.UserDto;
import com.orion.account.serviceaccountmanager.services.AccountService;
import com.orion.account.serviceaccountmanager.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountServiceController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {

        if(!validAccountRequest(accountRequestDTO)){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Invalid Parameters");
            return ResponseEntity.status(HttpStatus.OK).body(errorResponseDTO);
        }

        Optional<UserDto> optionalUserDto = userService.getUserbyId(accountRequestDTO.getCustomerId());
        if(optionalUserDto.isEmpty()){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("Customer Not Found");
            return ResponseEntity.status(HttpStatus.OK).body(errorResponseDTO);
        }else{
            accountService.createAccount(accountRequestDTO.getCustomerId(),accountRequestDTO.getCredit());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private boolean validAccountRequest(AccountRequestDTO accountRequestDTO) {

        if(accountRequestDTO.getCredit()!=null && accountRequestDTO.getCustomerId()!=null && accountRequestDTO.getCredit()>=0){
            return true;
        }
        return false;
    }
}
