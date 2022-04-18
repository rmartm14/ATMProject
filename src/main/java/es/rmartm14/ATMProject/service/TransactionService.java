package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.Transaction;

/**
 * Transaction Service to manage bank transactions
 *
 * @author Rafael Martinez
 */
public interface TransactionService {

    /**
     * Transaction saver
     * @param transaction transaction to save
     */
    void makeTransaction(final Transaction transaction);
}
