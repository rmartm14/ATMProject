package es.rmartm14.ATMProject.service;

import es.rmartm14.ATMProject.model.ATMBean;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.model.Transaction;
import es.rmartm14.ATMProject.repositories.ATMRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public Transaction withdrawMoney(Account account, Long money) {
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
        }
        return transaction;
    }

    private Transaction createTransaction(Account account, ATMBean atmBean, Long money) {
        account.setOverdraft(account.getBalance() < money ? account.getOverdraft() - (account.getBalance() - money) : account.getOverdraft());
        account.setBalance(account.getBalance() < money ? 0 : account.getBalance() - money);
        Transaction transaction = null;
        try {
            transaction = this.checkBills(account, atmBean, money);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

    private Transaction checkBills(Account account, ATMBean atmBean, Long money) throws Exception {
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
                throw new Exception();
            }
        }
        return transaction;
    }

}
