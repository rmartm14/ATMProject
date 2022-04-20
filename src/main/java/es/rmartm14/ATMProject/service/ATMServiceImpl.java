package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.exception.CustomException;
import es.rmartm14.ATMProject.model.ATMBean;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.model.Transaction;
import es.rmartm14.ATMProject.repositories.ATMRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * ATM Service Implementation
 *
 * @author Rafael Martinez
 */
@Component
public class ATMServiceImpl implements ATMService{

    private final ATMRepository atmRepository;
    private final AccountService accountService;

    /**
     * Constructor for having the atm repository created
     *
     * @param atmRepository  atm repository
     * @param accountService accountService
     */
    public ATMServiceImpl(ATMRepository atmRepository, AccountService accountService) {
        this.atmRepository = atmRepository;
        this.accountService = accountService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Transaction withdrawMoney(Account account, Long money) throws CustomException {
        List<ATMBean> atms = this.atmRepository.findAll();
        Transaction transaction = null;
        ATMBean atmBean = !atms.isEmpty() ? atms.get(0) : null;
        if(atmBean != null && atmBean.getTotalMoney() > money){
            if ((account.getBalance() + account.getOverdraft()) > money && money >= 5){
                transaction = this.createTransaction(account, atmBean, money);
                atmBean.calculateTotalMoney();

                this.atmRepository.deleteAll();
                this.atmRepository.save(atmBean);
                this.accountService.saveAccountMovement(account);
            }
            else{
                throw new CustomException("Cannot make the current transaction: Your account doesn't have that funds", "400", "Error in the transaction");
            }
        }
        else{
            throw new CustomException("Cannot make the current transaction: ATM Bean is not created or has not enough money", "400", "Error in the transaction");
        }
        return transaction;
    }

    private Transaction createTransaction(Account account, ATMBean atmBean, Long money) throws CustomException {
        account.setOverdraft(account.getBalance() < money ? account.getOverdraft() - (account.getBalance() - money) : account.getOverdraft());
        account.setBalance(account.getBalance() < money ? 0 : account.getBalance() - money);
        Transaction transaction = null;
        transaction = this.checkBills(account, atmBean, money);

        return transaction;
    }

    private Transaction checkBills(Account account, ATMBean atmBean, Long money) throws CustomException {
        Transaction transaction = new Transaction(account.getAccountNumber().toString(), money, 0,0,0,0);

        while(money > 0 ){
            if(money >= 50 && atmBean.getBill50() > 0){
                money = money - 50;
                transaction.setBill50(transaction.getBill50() + 1);
                atmBean.setBill50(atmBean.getBill50()-1);
            }
            else if(money >= 20 && atmBean.getBill20() > 0){
                money = money - 20;
                transaction.setBill20(transaction.getBill20() + 1);
                atmBean.setBill20(atmBean.getBill20()-1);
            }
            else if(money >= 10 && atmBean.getBill10() > 0){
                money = money - 10;
                transaction.setBill10(transaction.getBill10() + 1);
                atmBean.setBill10(atmBean.getBill10()-1);
            }else if(money >= 5 && atmBean.getBill5() > 0){
                money = money - 5;
                transaction.setBill5(transaction.getBill5() + 1);
                atmBean.setBill5(atmBean.getBill5()-1);
            }
            else{
                throw new CustomException("Internal Error", "400", "Internal Error");
            }
        }
        return transaction;
    }

}
