package com.nisbank.accounts.functions;

import com.nisbank.accounts.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateNotification(IAccountService accountsService) {
        return accountNumber -> {
            log.info("Updating Notification status for the account number : " + accountNumber.toString());
            accountsService.updateNotificationStatus(accountNumber);
        };
    }

}
