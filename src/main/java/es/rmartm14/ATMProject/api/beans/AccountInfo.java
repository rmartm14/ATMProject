package es.rmartm14.ATMProject.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Account Info bean for API
 *
 * @author Rafael Martínez
 */
@Data
@AllArgsConstructor
public class AccountInfo implements Serializable {

    private Long accountNumber;
    private Double balance;
    private Double overdraft;
}
