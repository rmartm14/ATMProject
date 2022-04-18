package es.rmartm14.ATMProject.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Bean to take info to login
 *
 * @author Rafael Martinez
 */
@Data
@AllArgsConstructor
public class AccountBodyRequest implements Serializable {

    private Long accountNumber;
    private Integer pin;
}
