package accounts.repositories;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest

class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testFindByAccountHolder() {
        Account account = new Account("1337",10000,"derri");
        entityManager.persist(account);
        entityManager.flush();

        Account check = accountRepository.findByAccountHolder("derri");

        assertThat(check.getAccountHolder()).isEqualTo(account.getAccountHolder());
    }

    @Test
    void testFindAccountByAccountNumber() {
        Account account = new Account("1337",10000,"1337");
        entityManager.persist(account);
        entityManager.flush();

        Account check = accountRepository.findAccountByAccountNumber("1337");
        assertThat(check.getAccountNumber()).isEqualTo(account.getAccountNumber());
    }

}

