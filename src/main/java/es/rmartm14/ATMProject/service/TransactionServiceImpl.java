package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.Transaction;
import es.rmartm14.ATMProject.repositories.TransactionRepository;
import org.springframework.stereotype.Component;

/**
 * Transaction Service Implementation
 *
 * @author Rafael Martinez
 */
@Component
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;

    /**
     * Transaction Service to interact with transactions
     * @param transactionRepository transaction repository
     */
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void makeTransaction(Transaction transaction) {
        if(transaction != null) this.transactionRepository.save(transaction);
    }
}
