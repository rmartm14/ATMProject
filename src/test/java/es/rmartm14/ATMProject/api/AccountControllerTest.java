package es.rmartm14.ATMProject.api;

import com.mongodb.assertions.Assertions;
import es.rmartm14.ATMProject.api.beans.AccountInfo;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.repositories.AccountRepository;
import es.rmartm14.ATMProject.service.AccountService;
import es.rmartm14.ATMProject.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AccountControllerTest {

    @Test
    void loginTest() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new AccountServiceImpl(accountRepository);
        AccountController accountController = new AccountController(accountService);
        Mockito.when(accountRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(new Account(1234L,1234,1d,1d));

        Assertions.assertTrue(accountController.login(new AccountInfo(1234L, 1234)).getBody());


    }
}
