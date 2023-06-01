package accounts.controller;

import accounts.repositories.AccountRepository;
import accounts.service.AccountDTO;
import accounts.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @MockBean
    AccountRepository repository;

    @Test
    public void testGetAccountByAccountNumber() throws Exception {
        Mockito.when(accountService.getAccount("1337")).thenReturn(new AccountDTO("1337",10000,"derri"));

        mockMvc.perform(MockMvcRequestBuilders.get("/account/1337"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("1337"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountHolder").value("derri"));
    }


}
