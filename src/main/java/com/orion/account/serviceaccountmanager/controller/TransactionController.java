package com.orion.account.serviceaccountmanager.controller;

import com.orion.account.serviceaccountmanager.dto.ErrorResponseDTO;
import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.exception.AccountException;
import com.orion.account.serviceaccountmanager.properties.TransactionPropertiesMessages;
import com.orion.account.serviceaccountmanager.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    private TransactionPropertiesMessages transactionPropertiesMessages;

    public TransactionController(TransactionService transactionService, TransactionPropertiesMessages transactionPropertiesMessages) {
        this.transactionService = transactionService;
        this.transactionPropertiesMessages = transactionPropertiesMessages;
    }

    @PostMapping("/create-transaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        log.info("m=createTransaction, accountId:{} , transactionId:{}, amount:{}, type:type",transactionRequestDTO.getAccountId(),
                transactionRequestDTO.getTransactionId(),transactionRequestDTO.getAmount(), transactionRequestDTO.getType());

        if(!validTransactionRequest(transactionRequestDTO)){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(transactionPropertiesMessages.getIncorrectformatrequest());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

        try{
            Optional<String> optional = transactionService.generateTransaction(transactionRequestDTO.getAccountId(),transactionRequestDTO.getAmount(),transactionRequestDTO.getType());
            if(optional.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(optional.get());
            }else{
                ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(transactionPropertiesMessages.getTransactionnotcreated());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDTO);
            }
        }catch (AccountException e){
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(transactionPropertiesMessages.getAccountnotfound());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
        }

    }

    private boolean validTransactionRequest(TransactionRequestDTO transactionRequestDTO) {
        if(StringUtils.isEmpty(transactionRequestDTO.getType()) || transactionRequestDTO.getAccountId()==null || transactionRequestDTO.getAmount()==null  ){
            return false;
        }
        if(transactionRequestDTO.getAmount()<0){
            return false;
        }
        return true;
    }


}
