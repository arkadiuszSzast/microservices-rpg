package com.microservices.rpg.zuul.config;

import com.microservices.rpg.zuul.account.AccountClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.logging.Logger;

@Configuration
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private Logger logger = Logger.getLogger(LoginListener.class.getName());

    private final AccountClient accountClient;

    public LoginListener(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        var accountId = ((OAuth2Authentication) event.getAuthentication()).getOAuth2Request().getClientId();
        logger.info(String.format("User %s successful authenticated", accountId));
        accountClient.setLastLogin();
    }
}
