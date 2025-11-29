package br.com.biosecure.model.client;

public class InvalidCnpjException extends IllegalArgumentException {
    public InvalidCnpjException(String message) {
        super(message);
    }
}
