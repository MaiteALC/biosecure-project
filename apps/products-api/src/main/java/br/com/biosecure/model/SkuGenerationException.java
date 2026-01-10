package br.com.biosecure.model;

public class SkuGenerationException extends IllegalArgumentException {
    public SkuGenerationException(String message) {
        super(message);
    }
    
    public SkuGenerationException(String message, Object invalidObj) {
        super(message + "\n Unknow type provided: " + invalidObj.getClass().getSimpleName());
    }
}
