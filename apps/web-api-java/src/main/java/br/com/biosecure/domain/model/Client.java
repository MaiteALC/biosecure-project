

import java.util.UUID;

public class  Client {
    private String corporateName;
    private UUID id;
    private String cnpj;
    private String adress;
    private String email;
    
    public Client(String corporateName, String cnpj, String adress, String email) {
        if (corporateName == null || corporateName.equals("")) {
            throw  new IllegalArgumentException("Please enter corporate name.");
        }
        
        if (!validateCnpj(cnpj)) {
            throw new IllegalArgumentException("Please enter valid CNPJ number.");
        }

        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Please enter a valid corporate email.");
        }
        
        this.corporateName = corporateName;
        this.id = UUID.randomUUID();
        this.cnpj = cnpj;
        this.adress = adress;
        this.email = email;

    }
    
    private boolean validateCnpj(String cnpj) {
        if (cnpj.equals("") || cnpj == null || cnpj.length() != 14) {
            return false;
        }

        else {
            if (!Character.isDigit(cnpj.charAt(13)) || !Character.isDigit(cnpj.charAt(12))) {
                return false;
            }
        }
        
        for(char c : cnpj.toCharArray() ) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        
        return true;
    }

    private boolean validateEmail(String email) {
        if (email == null || email.equals("") || !email.contains("@")) {
            return false;
        }
        
        return true;
    }
    
    public void updateAdress(String newAdress) {
        this.adress = newAdress;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;

        int result = 1;

        result = prime * result + ((corporateName == null) ? 0 : corporateName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());

        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
    
        Client other = (Client) obj;
        
        if (!id.equals(other.id) || !cnpj.equals(other.cnpj)) {
            return false;
        }
        
        return true;
    }

    public String getCorporateName() {return corporateName;}
    public UUID getId() {return id;}
    public String getCnpj() {return cnpj;}
    public String getAdress() {return adress;}
    public String getEmail() {return email;}
    
}