package br.com.biosecure.model.client;

public class InvalidAddressException extends IllegalArgumentException {
    private final String field;

    public InvalidAddressException(String invalidField) {
        super("The field '" + invalidField + "' is invalid");

        this.field = invalidField;
    }

    public String getInvalidField() {
        return field;
    }
}
