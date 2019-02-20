package com.microservices.rpg.accountservice.account;

import com.microservices.rpg.accountservice.account.domain.Account;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountCreateServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountCreateService accountCreateService;

    @Test
    public void shouldAddNewAccount() {

        //arrange
        var map = new HashMap<String, Object>();
        var accountId = "00uj41y0cei3z4hhh0h7";
        map.put("sub", accountId);
        var name = "accountName";
        map.put("name", name);
        var email = "account@mail.com";
        map.put("email", email);
        var accountToReturn = new Account(accountId, name, email);
        when(accountRepository.save(any(Account.class))).thenReturn(accountToReturn);

        //act
        var account = accountCreateService.create(map);

        //assert
        assertThat(account.getId()).isEqualTo(accountId);
        assertThat(account.getName()).isEqualTo(name);
        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getCreatedDate()).isEqualToIgnoringSeconds(LocalDateTime.now());
        assertThat(account.getLastLogInDate()).isNull();
    }
}
