package es.rmartm14.ATMProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class to extract the different transactions
 *
 * @author Rafael Martinez
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseDocument {

    private String accountId;
    private Long money;
    private Integer bill5;
    private Integer bill10;
    private Integer bill20;
    private Integer bill50;
}
