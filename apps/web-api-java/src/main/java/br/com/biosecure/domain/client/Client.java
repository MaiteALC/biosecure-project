package br.com.biosecure.domain.client;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Client {
    private String corporateName;
    private final UUID id;
    private final Cnpj cnpj;
    private Address address;
    private String email;
    
    public Client(String corporateName, Cnpj cnpj, Address address, String email) {
        if (corporateName.isBlank()) {
            throw new InvalidClientAttributeException("corporate name");
        }
        
        if (!validateEmail(email)) {
            throw new InvalidClientAttributeException("email");
        }

        this.corporateName = corporateName;
        this.cnpj = cnpj;
        this.id = UUID.randomUUID();
        this.address = address;
        this.email = email;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        Client other = (Client) obj;

        return id.equals(other.id) && cnpj.equals(other.cnpj);
    }

    @Override
    public String toString() {
        return "Client [corporate name: " + corporateName + ", CNPJ: " + cnpj.getFormattedNumber() + ", ID: " + id + ", address: " + address + ", email: " + email + "]";
    }

    private boolean validateEmail(String email) {
        if (email.isBlank()) {
            return false;
        }

        final String REGEX = "^[A-Za-z0-9._%+-]+@(?!(gmail\\.com|hotmail\\.com|outlook\\.com|yahoo\\.com|live\\.com)$)[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public String getCorporateName() {
        return corporateName;
    }

    public UUID getId() {
        return id;
    }

    public Cnpj getCnpj() {
        return cnpj;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setCorporateName(String newName) {
        if (newName.isBlank()) {
            throw new InvalidClientAttributeException("corporate name");
        }

        this.corporateName = newName;
    }
    public void setEmail(String newEmail) {
        if (!validateEmail(newEmail)) {
            throw new InvalidClientAttributeException("email");
        }

        this.email = newEmail;
    }

    public void setAddress(Address newAddress) {
        this.address = newAddress;
    }
}