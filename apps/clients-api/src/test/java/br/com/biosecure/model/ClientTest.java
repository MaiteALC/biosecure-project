package br.com.biosecure.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ClientTest {
    private final Address validAddress = new Address("Test State", "Test City", "Test", "Teststreet", 321, "12345-678");
    private final Cnpj validCnpj = new Cnpj("53.297.928/0001-46");
    private final String validEmail = "teste1@biosecure.com.br";

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"    ", "test2@gmail.com", "test2@hotmail.com", "test3@outlook.com"})
    public void clientCreationMustFailOnEmail(String invalidEmail) {
        InvalidClientAttributeException exception = assertThrows(InvalidClientAttributeException.class,
            () -> new Client("Zepto Lab", validCnpj, validAddress, invalidEmail)
        );

        assertEquals("email", exception.getInvalidAttribute());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"     ", "A", "1"})
    public void clientCreationMustFailOnName(String invalidName) {
        InvalidClientAttributeException exception = assertThrows(InvalidClientAttributeException.class,
            () -> new Client(invalidName, validCnpj, validAddress, validEmail)
        );

        assertEquals("name", exception.getInvalidAttribute());
    }
}