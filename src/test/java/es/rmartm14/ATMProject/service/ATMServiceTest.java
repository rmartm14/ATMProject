package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.ATMBean;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.model.Transaction;
import es.rmartm14.ATMProject.repositories.ATMRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ATMServiceTest {

    @Test
    void testTransaction() {
        ATMRepository atmRepository = Mockito.mock(ATMRepository.class);
        ATMBean atm = new ATMBean();
        Mockito.when(atmRepository.findAll()).thenReturn(List.of(atm));
        AccountService accountService = Mockito.mock(AccountService.class);
        Account account = new Account(123456789L, 1234, 800d, 200d, "");
        ATMService atmService = new ATMServiceImpl(atmRepository, accountService);

        Transaction transaction = atmService.withdrawMoney(account, 400L);
        Assertions.assertEquals(0, transaction.getBill5());
        Assertions.assertEquals(0, transaction.getBill10());
        Assertions.assertEquals(0, transaction.getBill20());
        Assertions.assertEquals(8, transaction.getBill50());
        Assertions.assertEquals(400, transaction.getMoney());
        Assertions.assertEquals(account.getAccountNumber().toString(), transaction.getAccountId());

        Assertions.assertEquals(400, account.getBalance());
        //assert account token isnt changed
        Assertions.assertEquals("", account.getAccessToken());
        Assertions.assertEquals(2, atm.getBill50());
        Assertions.assertEquals(20, atm.getBill5());
        Assertions.assertEquals(30, atm.getBill10());
        Assertions.assertEquals(30, atm.getBill20());

        Assertions.assertEquals(1100, atm.getTotalMoney());

    }
}



