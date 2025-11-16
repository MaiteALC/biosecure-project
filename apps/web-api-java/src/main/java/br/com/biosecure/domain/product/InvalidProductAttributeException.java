package br.com.biosecure.domain.product;

public class InvalidProductAttributeException extends IllegalArgumentException {
    private final String invalidAttribute;

    public InvalidProductAttributeException(String attribute) {
        super("The field '" + attribute + "' musn't be empty");
        this.invalidAttribute = attribute;
    }

    public String getInvalidAttribute() {
        return invalidAttribute;
    }
}
