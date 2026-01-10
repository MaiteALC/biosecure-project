package br.com.biosecure.model;

public class InvalidCnpjException extends IllegalArgumentException {
    public InvalidCnpjException(String message) {
        super(message);
    }
}
