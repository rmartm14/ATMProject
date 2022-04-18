package es.rmartm14.ATMProject.api;

import com.mongodb.assertions.Assertions;
import es.rmartm14.ATMProject.api.beans.AccountBodyRequest;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.repositories.AccountRepository;
import es.rmartm14.ATMProject.service.ATMService;
import es.rmartm14.ATMProject.service.AccountService;
import es.rmartm14.ATMProject.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AccountControllerTest {

    @Test
    void loginTest() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        AccountService accountService = new AccountServiceImpl(accountRepository);
        AccountController accountController = new AccountController(accountService, Mockito.mock(ATMService.class), null);
        Mockito.when(accountRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(new Account(1234L,1234,1d,1d, ""));

        Assertions.assertNotNull(accountController.login(new AccountBodyRequest(1234L, 1234)).getBody());

    }
}
