package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.Account;

/**
 * Account service to manage every operation of the accountNumber
 *
 * @author Rafael Martinez
 */
public interface AccountService {
    /**
     * Checks the login and returns a usable token
     * @param accountNumber accountNumber to access
     * @param pin pin to access
     * @return token string
     */
    String checkLogin(final Long accountNumber, final Integer pin);

    /**
     * Method to return the information of the requestedAccount
     *
     * @param auth authorization token
     * @return account to return from mongo
     */
    Account getAccountInfo(final String auth);

    /**
     * Saves an account movement
     *
     * @param account account movement
     */
    void saveAccountMovement(Account account);
}
