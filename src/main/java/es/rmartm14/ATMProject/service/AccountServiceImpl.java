package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.repositories.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Account Service Implementation
 *
 * @author Rafael Martinez
 */
@Component
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    /**
     * Constructor to autowire the account repository
     * @param accountRepository account repository autowired
     */
    public AccountServiceImpl(final AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkLogin(Long accountNumber, Integer pin) {
        Account account = this.accountRepository.findByAccountNumber(accountNumber);
        return account != null && Objects.equals(account.getPin(), pin);
    }
}
