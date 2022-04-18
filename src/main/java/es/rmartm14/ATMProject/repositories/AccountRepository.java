package es.rmartm14.ATMProject.repositories;

import es.rmartm14.ATMProject.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface to use mongo repositories
 *
 * @author Rafael Martínez
 */
public interface AccountRepository extends MongoRepository<Account, String> {

    /**
     * Method to find an account by its account number
     *
     * @param accountNumber account number to filter
     * @return account
     */
    Account findByAccountNumber(Long accountNumber);

    /**
     * Access token filter method from the repository
     *
     * @param accessToken access token to retrieve an account
     *
     * @return account to return
     */
    Account findByAccessToken(String accessToken);
}
