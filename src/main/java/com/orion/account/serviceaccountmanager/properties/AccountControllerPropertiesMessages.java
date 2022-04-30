package com.orion.account.serviceaccountmanager.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(chain = true)
@ConfigurationProperties("orion.account.controller.message")
public class AccountControllerPropertiesMessages {

    private String error;
    private String messageok;
    private String statusok;
    private String messageparamtererror;
    private String customernotfound;
    private String accountnotcreated;
    private String transactionnotcreated;
}
