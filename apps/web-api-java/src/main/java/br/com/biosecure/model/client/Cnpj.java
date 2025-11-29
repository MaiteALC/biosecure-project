package br.com.biosecure.model.client;

public class Cnpj {
    private final String number; 
    private final String formattedNumber;

    public static final int SIZE = 14;
    
    public Cnpj(String number) {
        String cleanNumber = clearFormat(number);

        validateVerifierDigits(cleanNumber);

        this.number = cleanNumber;
        this.formattedNumber = formatCnpj(cleanNumber);
    }

    private String clearFormat(String formatted) {
        return formatted.replaceAll("\\D", "");
    }

    private String formatCnpj(String nonFormatted) {
        if (nonFormatted.length() != SIZE) {
            throw new InvalidCnpjException("Invalid size (" + nonFormatted.length() + ")");
        }

        StringBuilder sb = new StringBuilder(nonFormatted);
    
        sb.insert(2, ".")
        .insert(6, ".")
        .insert(10, "/")
        .insert(15, "-");
        
        return sb.toString();
    }
    
    private void validateVerifierDigits(String number) {
        // TODO Since July 2026 the CNPJ format going to be modified. Remember to change the logic of this method.

        String unformatted = clearFormat(number);

        if (unformatted.isBlank() || unformatted.length() != SIZE) {
            throw new InvalidCnpjException("Invalid size (" + number.length() + ")");
        }

        if (!Character.isDigit(unformatted.charAt(13)) || !Character.isDigit(unformatted.charAt(12))) {
            throw new InvalidCnpjException("The two last digits must be numbers");
        }
        
        int[] firstWeights = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] secondWeights = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
        int[] cnpjInts = new int[14];
        
        int result = 0;
        int rest = 0;
        int digit = 0;

        for (int i = 0; i < SIZE; i++) {
            cnpjInts[i] =  unformatted.charAt(i) - '0'; // Fast way to convert char to int (using the numeric values of characters in ASCII table)
        }

        // Validation of first verifier digit
        for (int i = 0; i < 12; i++) {
           result += firstWeights[i] * cnpjInts[i];
        }

        rest = result % 11;
        digit = (rest < 2) ? 0 : (11 - rest);
        
        if (cnpjInts[12] != digit) {
            throw new InvalidCnpjException("The first verifier digit is invalid");
        }

        // Validation of second verifier digit
        result = digit = rest = 0;     

        for (int i = 0; i < 13; i++) {
           result += secondWeights[i] * cnpjInts[i];
        }

        rest = result % 11;
        digit = (rest < 2) ? 0 : (11 - rest);

        if (cnpjInts[13] != digit) {
            throw new InvalidCnpjException("The second verifier digit is invalid");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
    
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Cnpj other = (Cnpj) obj;

        return number.equals(other.number);
    }

    public String getNumber() {
        return number;
    }

    public String getFormattedNumber() {
        return formattedNumber;
    }
}