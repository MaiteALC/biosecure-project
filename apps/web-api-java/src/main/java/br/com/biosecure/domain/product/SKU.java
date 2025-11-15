package br.com.biosecure.domain.product;

public class SKU {
    private String code;

    private SKU(String code) {
        this.code = code;
    }

    public static SKU generateFor(Product product) {
        String classStr = product.getClass().getSimpleName().toUpperCase();

        String value = String.valueOf(classStr.toCharArray(), 0, 2);

        // Obtaining the ASCII value of the first and last characters (in upper case)
        String n1 = String.valueOf((int) classStr.charAt(0));
        String n2 = String.valueOf((int) classStr.charAt(classStr.length() -1));

        value += n1 + n2 + "-";

        // TODO finish the implementation of this method (based on their another atributes)
        
        return new SKU(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SKU sku = (SKU) o;

        return code.equals(sku.code);
    }

    public String getCode() {
        return code;
    }
}
