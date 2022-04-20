package es.rmartm14.ATMProject.api;

import es.rmartm14.ATMProject.exception.CustomException;
import es.rmartm14.ATMProject.api.beans.AccountBodyRequest;
import es.rmartm14.ATMProject.api.beans.AccountInfo;
import es.rmartm14.ATMProject.model.Account;
import es.rmartm14.ATMProject.model.Transaction;
import es.rmartm14.ATMProject.service.ATMService;
import es.rmartm14.ATMProject.service.AccountService;
import es.rmartm14.ATMProject.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Rest controller to involve operations with the account
 *
 * @author Rafael Martinez
 */
@RestController
public class AccountController {
    private final AccountService accountService;
    private final ATMService atmService;
    private final TransactionService transactionService;

    /**
     * Basic constructor of the account Controller
     *
     * @param accountService     accountRepository to access account on mongo db
     * @param atmService         atm service in order to interact with the atm
     * @param transactionService transaction service
     */
    public AccountController(AccountService accountService, ATMService atmService, TransactionService transactionService) {
        this.accountService = accountService;
        this.atmService = atmService;
        this.transactionService = transactionService;
    }

    /**
     * Classic login with an account number and an accountId
     *
     * @param accountInfo account info request in petition body
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountBodyRequest accountInfo) {
        final String token = this.accountService.checkLogin(accountInfo.getAccountNumber(), accountInfo.getPin());
        if(token != null){
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account info provided is not correct. Please, try again");
    }

    /**
     * Account status method in order to know how the account is going
     *
     * @param authorization authorization param
     *
     * @return returns the accountInfo
     */
    @GetMapping("/account/info")
    public ResponseEntity<AccountInfo> accountStatus(@RequestHeader String authorization){
        final Account account = this.accountService.getAccountInfo(authorization);
        if (account != null){
            AccountInfo accountInfo = new AccountInfo(account.getAccountNumber(), account.getBalance(), account.getOverdraft());
            return new ResponseEntity<>(accountInfo, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account Info cannot be found. Please, check your authorization.");
    }

    /**
     * Method to get the money from the account. It has to take into account the money that has the atm at the moment and the money that
     * the account has.
     *
     * @param authorization authorization string needed to perform operations
     * @param money money to withdraw
     * @throws CustomException customException
     * @return transaction information
     */
    @PostMapping("/account/withdrawal")
    public ResponseEntity<Transaction> accountWithdrawal(@RequestHeader String authorization, @RequestBody Long money) throws CustomException {
        final Account account = this.accountService.getAccountInfo(authorization);
        try {
            final Transaction transaction = this.atmService.withdrawMoney(account, money);
            if(transaction != null){
                this.transactionService.makeTransaction(transaction);
                return new ResponseEntity<>(transaction, HttpStatus.OK);
            }
        }  catch (CustomException ex) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        return null;
    }

}
