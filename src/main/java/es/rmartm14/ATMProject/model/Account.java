package es.rmartm14.ATMProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Bean to define an account
 *
 * @author Rafael Martinez
 */
@Data
@Document
@AllArgsConstructor
public class Account extends BaseDocument{
    private Long accountNumber;
    private Integer pin;
    private Double balance;
    private Double overdraft;
    private String accessToken;
}
