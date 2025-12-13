package br.com.biosecure.model.client;

import java.util.ArrayList;
import java.util.List;
import br.com.biosecure.utils.InvalidAttributeException;
import br.com.biosecure.utils.ValidationException;

public class InvalidClientAttributeException extends InvalidAttributeException {
    public InvalidClientAttributeException(String attribute) {
        super(attribute, "Client attribute '" + attribute + "' is invalid!");
        
        this.invalidAttribute = attribute;
    }
    
    public InvalidClientAttributeException(List<String> invalidAttributes) {
        super(invalidAttributes, "Client attributes: " + invalidAttributes.toString() + " are invalid");
        
        this.invalidAttributesArray.addAll(invalidAttributes);
    }
    
    public InvalidClientAttributeException(ArrayList<ValidationException> validationExceptions) {
        super(validationExceptions, createCustomMessaege(validationExceptions));
    }

    private static String createCustomMessaege(ArrayList<ValidationException> errors) {
        ArrayList<String> errorsStr = new ArrayList<>();
        
        for (ValidationException currentError : errors) {
            errorsStr.add(currentError.getInvalidProperty() + "(" + currentError.getMessage() + ")");
        }

        String message = "Client attributes: \n";

        for (String str : errorsStr) {
            message += "\t - " + str + "\n";
        }

        message += "are invalid.";

        return message;
    }
}