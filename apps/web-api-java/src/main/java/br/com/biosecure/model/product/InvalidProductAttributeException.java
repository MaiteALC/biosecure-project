package br.com.biosecure.model.product;

import java.util.ArrayList;
import java.util.List;
import br.com.biosecure.utils.InvalidAttributeException;
import br.com.biosecure.utils.ValidationException;

public class InvalidProductAttributeException extends InvalidAttributeException {
    public InvalidProductAttributeException(String attribute) {
        super(attribute, "Product attribute '" + attribute + "' is invalid!");
    }
    
    public InvalidProductAttributeException(List<String> invalidAttributes) {
        super(invalidAttributes, "Invalid product attributes: " + invalidAttributes.toString());
    }
    
    public InvalidProductAttributeException(ArrayList<ValidationException> validationExceptions) {
        super(validationExceptions, createCustomMessaege(validationExceptions));
    }

    private static String createCustomMessaege(ArrayList<ValidationException> errors) {
        ArrayList<String> errorsStr = new ArrayList<>();
        
        for (ValidationException currentError : errors) {
            errorsStr.add(currentError.getInvalidProperty() + " (" + currentError.getMessage() + ")");
        }

        String message = "Invalid product attributes:\n";

        for (String str : errorsStr) {
            message += "\t - " + str + "\n";
        }

        return message;
    }
}