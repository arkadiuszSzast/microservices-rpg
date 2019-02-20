package com.microservices.rpg.accountservice.account;

import com.microservices.rpg.accountservice.account.domain.Account;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class AccountUpdateLastLogInServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountUpdateLastLogInService accountUpdateLastLogInService;

    @Test
    public void shouldUpdateLastLogInDate() {

        //arrange
        var account = new Account("00uj41y0cei3z4hhh0h7", "name", "mail");
        account.setCreatedDate(LocalDateTime.of(2018, 5, 12, 10, 11));
        account.setLastLogInDate(LocalDateTime.of(2019, 1, 8, 9, 22));

        //act
        accountUpdateLastLogInService.update(account);

        //assert
        assertThat(account.getLastLogInDate()).isEqualToIgnoringSeconds(LocalDateTime.now());
    }
}
