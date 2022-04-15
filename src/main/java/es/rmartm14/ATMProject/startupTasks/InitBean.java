package es.rmartm14.ATMProject.startupTasks;

import es.rmartm14.ATMProject.model.ATMBean;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.repositories.ATMRepository;
import es.rmartm14.ATMProject.repositories.AccountRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Class to initialize data on MongoDb
 *
 * @author Rafael Martinez
 */
@Component
public class InitBean implements ApplicationListener<ContextRefreshedEvent> {

    private final AccountRepository acRepo;
    private final ATMRepository atmRepo;

    /**
     * Basic class constructor to have the
     * @param accountRepository accountRepository
     * @param atmRepository atmRepository
     */
    public InitBean(AccountRepository accountRepository, ATMRepository atmRepository){
        this.acRepo = accountRepository;
        this.atmRepo = atmRepository;
    }

    /**
     * On application Restart, we insert the basic account and ATM bean
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(atmRepo.findAll().isEmpty()){
            atmRepo.insert(new ATMBean());
        }
        if(acRepo.findAll().isEmpty()){
            this.insertAccounts();
        }
    }
    private void insertAccounts() {
        Account a = new Account(123456789L, 1234, 800d,200d);
        Account b = new Account(987654321L, 1234, 1230d, 150d);
        acRepo.insert(a);
        acRepo.insert(b);
    }
}
