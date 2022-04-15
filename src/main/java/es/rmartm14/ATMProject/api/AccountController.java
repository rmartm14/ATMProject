package es.rmartm14.ATMProject.api;

import es.rmartm14.ATMProject.api.beans.AccountInfo;
import es.rmartm14.ATMProject.repositories.AccountRepository;
import es.rmartm14.ATMProject.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to involve operations with the account
 *
 * @author Rafael Martinez
 */
@RestController
public class AccountController {

    private final AccountService accountService;

    /**
     * Basic constructor of the account Controller
     * @param accountService accountRepository to access account on mongo db
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Classic login with an account number and an accountId
     *
     * @param accountInfo account info request in petition body
     */
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody AccountInfo accountInfo) {
        return new ResponseEntity<>(this.accountService.checkLogin(accountInfo.getAccountNumber(), accountInfo.getPin()), HttpStatus.OK);
    }

}
