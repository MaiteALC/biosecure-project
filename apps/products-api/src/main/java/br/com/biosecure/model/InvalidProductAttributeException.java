package br.com.biosecure.model;

import java.util.ArrayList;
import br.com.biosecure.utils.InvalidAttributeException;
import br.com.biosecure.utils.ValidationException;

public class InvalidProductAttributeException extends InvalidAttributeException {
    public InvalidProductAttributeException(String attribute, String message) {
        super(attribute, message);
    }
    
    public InvalidProductAttributeException(ArrayList<ValidationException> validationExceptions) {
        super(validationExceptions);
    }
}