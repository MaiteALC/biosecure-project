package br.com.biosecure.model.product;

public class SKU {
    private final String code;

    public SKU(Product product) {
        this.code = generateFor(product);
    }

    private String generateFor(Product product) {
        String name = product.getName();

        String value = name.length() >= 3 ? name.substring(0, 4) : name;

        value += "-" + product.getPackagingType().getPackagingTypeCode();

        value += String.valueOf(product.getQtdPerPackage());

        value += product.getMeasureUnity() + "-";

        if (product instanceof Sanitizer sanitizer) {
            String actIng = sanitizer.getActiveIngredient();

            value += actIng.length() >= 3 ? actIng.substring(0, 4) : actIng;
        }

        if (product instanceof CultureMedia cultureMedia) {
            value += cultureMedia.isProtectOfLight() ? "F" : "N";

            value += String.valueOf(cultureMedia.getStorageConditions().getCode());
        }

        if (product instanceof SampleContainer sampleContainer) {
            if (sampleContainer instanceof SampleBag sampleBag) {
                value += sampleBag.hasIdentificationTag() ? "I" : "A";

                value += sampleBag.isStandUp() ? "U" : "D";
            }

            if (sampleContainer instanceof TestTube testTube) {
                value += testTube.isGraduated() ? "G" : "L";

                value += testTube.getBottomType().toString().charAt(0);
            }

            if (sampleContainer instanceof PetriDishes petriDishes) {
                value += petriDishes.getDiameter() + "X";

                value += String.valueOf(petriDishes.getHeight());
            }
        }

        // TODO finish the implementation of this method (based on another attributes of product subclasses)

        return value.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SKU otherSku = (SKU) obj;

        return code.equals(otherSku.code);
    }

    public String getSkuCode() {
        return code;
    }
}