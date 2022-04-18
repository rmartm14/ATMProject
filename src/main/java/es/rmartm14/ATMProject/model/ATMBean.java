package es.rmartm14.ATMProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Bean that defines the current bills that the ATM has
 *
 * @author Rafael Martínez
 */
@Data
@Document
@AllArgsConstructor
public class ATMBean {
    private int bill50;
    private int bill20;
    private int bill10;
    private int bill5;
    private double totalMoney;

    /**
     * Constructor to have default values
     */
    public ATMBean() {
        this.bill5 = 20;
        this.bill10 = 30;
        this.bill20 = 30;
        this.bill50 = 10;

        calculateTotalMoney();
    }


    public synchronized void calculateTotalMoney() {
        this.totalMoney = 5 * bill5 + 10 * bill10 + 20 * bill20 + 50 * bill50;
    }
}
