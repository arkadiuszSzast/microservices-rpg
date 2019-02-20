package com.microservices.rpg.accountservice.account;

import com.microservices.rpg.accountservice.account.domain.Account;
import com.microservices.rpg.accountservice.account.domain.AccountRepository;
import com.microservices.rpg.accountservice.config.OktaPrincipalExtractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class OktaPrincipalExtractorTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountCreateService accountCreateService;

    @InjectMocks
    private OktaPrincipalExtractor oktaPrincipalExtractor;

    @Test
    public void shouldExtractPrincipalWhenExistInDb() {

        //arrange
        var map = new HashMap<String, Object>();
        map.put("sub", "00uj41y0cei3z4hhh0h7");
        map.put("name", "joe");
        map.put("email", "joe@mail.com");
        var account = new Account("00uj41y0cei3z4hhh0h7", "joe", "joe@mail.com");
        when(accountRepository.findById(map.get("sub").toString())).thenReturn(Optional.ofNullable(account));

        //act
        var object = oktaPrincipalExtractor.extractPrincipal(map);

        //assert
        assertThat(object).isInstanceOf(Account.class);
        assertThat(((Account) object).getEmail()).isEqualTo(account.getEmail());
        assertThat(((Account) object).getName()).isEqualTo(account.getName());
        assertThat(((Account) object).getId()).isEqualTo(account.getId());
    }

    @Test
    public void shouldExtractPrincipalWhenNotExistInDb() {

        //arrange
        var map = new HashMap<String, Object>();
        map.put("sub", "00uj41y0cei3z4hhh0h7");
        map.put("name", "joe");
        map.put("email", "joe@mail.com");
        var account = new Account("00uj41y0cei3z4hhh0h7", "joe", "joe@mail.com");
        account.setCreatedDate(LocalDateTime.now());
        when(accountRepository.findById(map.get("sub").toString())).thenReturn(Optional.empty());
        when(accountCreateService.create(map)).thenReturn(account);

        //act
        var object = oktaPrincipalExtractor.extractPrincipal(map);

        //assert
        assertThat(object).isInstanceOf(Account.class);
        assertThat(((Account) object).getEmail()).isEqualTo(account.getEmail());
        assertThat(((Account) object).getName()).isEqualTo(account.getName());
        assertThat(((Account) object).getId()).isEqualTo(account.getId());
        assertThat(((Account) object).getCreatedDate()).isEqualToIgnoringSeconds(LocalDateTime.now());
        verify(accountCreateService, times(1)).create(map);
    }
}
