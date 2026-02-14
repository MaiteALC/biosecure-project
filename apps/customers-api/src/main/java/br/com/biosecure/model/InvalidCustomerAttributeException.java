package br.com.biosecure.model;

import java.util.ArrayList;
import br.com.biosecure.utils.InvalidAttributeException;
import br.com.biosecure.utils.ValidationException;

public class InvalidCustomerAttributeException extends InvalidAttributeException {
    public InvalidCustomerAttributeException(String attribute, String message) {
        super(attribute, message);
    }
    
    public InvalidCustomerAttributeException(ArrayList<ValidationException> validationExceptions) {
        super(validationExceptions);
    }
}