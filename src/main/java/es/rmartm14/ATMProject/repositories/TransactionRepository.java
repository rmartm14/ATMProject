package es.rmartm14.ATMProject.repositories;

import es.rmartm14.ATMProject.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
