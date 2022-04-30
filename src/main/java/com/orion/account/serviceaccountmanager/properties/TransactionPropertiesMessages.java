package com.orion.account.serviceaccountmanager.properties;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(chain = true)
@ConfigurationProperties("orion.transaction.message")
public class TransactionPropertiesMessages {

    private String incorrectformatrequest;

    private String transactionnotcreated;

    private String accountnotfound;
}
