package es.rmartm14.ATMProject.service;

/**
 * Account service to manage every operation of the accountNumber
 *
 * @author Rafael Martinez
 */
public interface AccountService {

    boolean checkLogin(final Long accountNumber, final Integer pin);
}
