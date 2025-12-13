package br.com.biosecure.utils;

import java.util.ArrayList;
import java.util.List;

public class InvalidAttributeException extends IllegalArgumentException {
    protected String invalidAttribute;
    protected ArrayList<String> invalidAttributesArray = new ArrayList<>();

    public InvalidAttributeException(String attributeName, String message) {
        super(message);

        this.invalidAttribute = attributeName;
    }
    
    public InvalidAttributeException(List<String> attributes, String message) {
        super(message);

        this.invalidAttributesArray.addAll(attributes);
    }

    public InvalidAttributeException(ArrayList<ValidationException> validationExceptions, String message) {
        super(message);
        
        for (ValidationException exception : validationExceptions) {
            this.invalidAttributesArray.add(exception.getInvalidProperty());   
        }
    }

    public String getInvalidAttribute() {
        if (invalidAttribute == null) {
            if (invalidAttributesArray.size() == 1) {
                return invalidAttributesArray.toString().replace("[", "").replace("]", "");
            }

            else {
                return invalidAttributesArray.toString();
            }
        }
        
        return  invalidAttribute;
    }

    public ArrayList<String> getInvalidAttributesArray() {
        if (invalidAttribute != null) {
            this.invalidAttributesArray.add(invalidAttribute);
        }

        return invalidAttributesArray;
    }
}
