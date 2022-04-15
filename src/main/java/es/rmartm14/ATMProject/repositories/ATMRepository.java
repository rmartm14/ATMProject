package es.rmartm14.ATMProject.repositories;

import es.rmartm14.ATMProject.model.ATMBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository to interact with ATM
 *
 * @author Rafael Martínez
 */
public interface ATMRepository extends MongoRepository<ATMBean, String> {
}
