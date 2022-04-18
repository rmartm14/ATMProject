package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.model.Transaction;

/**
 * ATM Service needed for using the bank account
 *
 * @author Rafael Martinez
 */
public interface ATMService {

    /**
     * Transaction with money to withdraw
     * @param account account to withdraw
     * @param money money to extract
     * @return transaction information
     */
    Transaction withdrawMoney(Account account, Long money);
}
