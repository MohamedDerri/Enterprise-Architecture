package accounts.service;



import accounts.domain.Account;
import accounts.repositories.AccountRepository;

import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)

public class AccountServiceTest {
    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountService accountService(){
            return new AccountService();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void setUp(){
        String accountNumber = "1337";
        Account acc = new Account("1337",10000,"derri");
        Optional<Account> accOptional = Optional.of(acc);
        Mockito.when(accountRepository.findById(accountNumber)).thenReturn(accOptional);


    }

    @Test
    public void accountWithValidNumber(){
        String accountNumber = "1337";
        AccountDTO found = accountService.getAccount(accountNumber);
        Account acc = new Account(found.getAccountNumber(),found.getBalance(),found.getAccountHolder());
        assertThat(acc.getAccountNumber()).isEqualTo(accountNumber);

    }


}

