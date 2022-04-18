package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.repositories.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

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
    public String checkLogin(Long accountNumber, Integer pin) {
        Account account = this.accountRepository.findByAccountNumber(accountNumber);
        //Token are made by a randomUUID + accountNumber
        if(Objects.equals(account.getAccountNumber(), accountNumber) && Objects.equals(pin, account.getPin())){
            final String token = UUID.randomUUID().toString() + accountNumber;
            account.setAccessToken(token);
            this.accountRepository.save(account);
            return token;
        }
        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Account getAccountInfo(String auth) {
        return this.accountRepository.findByAccessToken(auth);
    }

    @Override
    public void saveAccountMovement(Account account) {
        this.accountRepository.save(account);
    }


}
