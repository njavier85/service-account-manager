package com.orion.account.serviceaccountmanager.controller;

import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.entity.AccountEntity;
import com.orion.account.serviceaccountmanager.services.AccountService;
import com.orion.account.serviceaccountmanager.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/create-transaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        transactionService.generateTransaction(transactionRequestDTO.getAccountId(),transactionRequestDTO.getAmount(),transactionRequestDTO.getType());

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
