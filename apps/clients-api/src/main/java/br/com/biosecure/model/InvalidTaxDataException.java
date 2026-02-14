package br.com.biosecure.model;

import br.com.biosecure.utils.InvalidAttributeException;
import br.com.biosecure.utils.ValidationException;

import java.util.ArrayList;

public class InvalidTaxDataException extends InvalidAttributeException {
    public InvalidTaxDataException(String attributeName, String message) {
        super(attributeName, message);
    }

    public InvalidTaxDataException(ArrayList<ValidationException> exceptionsArray) {
        super(exceptionsArray);
    }
}
