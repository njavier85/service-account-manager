package com.orion.account.serviceaccountmanager.client;


import com.orion.account.serviceaccountmanager.dto.TransactionRequestDTO;
import com.orion.account.serviceaccountmanager.dto.TransactionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "transaction-internal-service", url = "${transaction-internal-service.api.url}")
public interface TransactionClient {

    @PostMapping("/create-transaction")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) ;
}
