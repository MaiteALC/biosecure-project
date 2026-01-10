package br.com.biosecure.model;

import java.util.ArrayList;
import br.com.biosecure.utils.InvalidAttributeException;
import br.com.biosecure.utils.ValidationException;

public class InvalidClientAttributeException extends InvalidAttributeException {
    public InvalidClientAttributeException(String attribute, String message) {
        super(attribute, message);
    }
    
    public InvalidClientAttributeException(ArrayList<ValidationException> validationExceptions) {
        super(validationExceptions);
    }
}