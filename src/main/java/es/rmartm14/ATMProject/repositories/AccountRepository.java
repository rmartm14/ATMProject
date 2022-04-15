package es.rmartm14.ATMProject.repositories;

import es.rmartm14.ATMProject.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interface to use mongo repositories
 *
 * @author Rafael Martínez
 */
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByAccountNumber(Long accountNumber);
}
