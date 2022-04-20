package es.rmartm14.ATMProject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Custom Exception for the different errors that can occur on the application
 *
 * @author Rafael Martinez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends Exception{

    private String message;
    private String code;
    private String name;
}
