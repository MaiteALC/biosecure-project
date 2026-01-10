package br.com.biosecure.utils;

import java.util.ArrayList;

public class InvalidAttributeException extends RuntimeException {
    protected String invalidAttribute;
    protected ArrayList<String> invalidAttributesArray = new ArrayList<>();

    protected InvalidAttributeException(String attributeName, String message) {
        super(message);

        this.invalidAttribute = attributeName;
    }

    protected InvalidAttributeException(ArrayList<ValidationException> exceptionsArray) {
        super(createCustomMessage(exceptionsArray));
        
        for (ValidationException exception : exceptionsArray) {
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

    private static String createCustomMessage(ArrayList<ValidationException> errors) {
        StringBuilder message = new StringBuilder("These attributes are invalids:\n");

        for (ValidationException currentError : errors) {
            message.append("\t - ");
            message.append(currentError.getInvalidProperty());

            message.append(" | "); 
            
            message.append(currentError.getMessage());
            message.append('\n');
        }

        return message.toString();
    }
}
