package br.com.biosecure.model;

import br.com.biosecure.utils.ErrorAggregator;
import br.com.biosecure.utils.NumberUtils;
import br.com.biosecure.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class Sanitizer extends Product {
    private final List<Ingredient> composition;
    private final PhysicalForm physicalForm;
    private final String registryNumber;
    private final String useIndications;
    private final double phLevel;
    private final boolean requiresDilution;
    private final double densityGramsPerMilliLiter;
    private final boolean flammable;
    private final Ingredient.ChemicalFamily mainChemicalFamily;

    private Sanitizer(SanitizerBuilder builder) {
        super(builder);

        this.composition = builder.composition;
        this.physicalForm = builder.physicalForm;
        this.registryNumber = builder.registryNumber;
        this.useIndications = builder.useIndications;
        this.phLevel = builder.phLevel;
        this.requiresDilution = builder.requiresDilution;
        this.densityGramsPerMilliLiter = builder.densityGramsPerMilliLiter;
        this.flammable = builder.flammable;
        this.mainChemicalFamily = builder.mainChemicalFamily;
    }

    public static SanitizerBuilder builder() {
        return new SanitizerBuilder();
    }

    public static final class SanitizerBuilder extends ProductBuilder<Sanitizer, SanitizerBuilder> {
        private List<Ingredient> composition;
        private PhysicalForm physicalForm;
        private String registryNumber;
        private String useIndications;
        private double phLevel;
        private boolean requiresDilution;
        private double densityGramsPerMilliLiter;
        private boolean flammable;
        private Ingredient.ChemicalFamily mainChemicalFamily;

        @Override
        protected SanitizerBuilder self() {
            return this;
        }

        public SanitizerBuilder composition(List<Ingredient> composition) {
            this.composition = composition;
            return this;
        }

        public SanitizerBuilder registryNumber(String registryNumber) {
            this.registryNumber = registryNumber;
            return this;
        }

        public SanitizerBuilder physicalForm(PhysicalForm physicalForm) {
            this.physicalForm = physicalForm;
            return this;
        }

        public SanitizerBuilder useIndications(String useIndications) {
            this.useIndications = useIndications;
            return this;
        }

        public SanitizerBuilder phLevel(double phLevel) {
            this.phLevel = phLevel;
            return this;
        }

        public SanitizerBuilder flammable(boolean flammable) {
            this.flammable = flammable;
            return this;
        }

        public SanitizerBuilder densityGramsPerMilliLiter(double densityGramsPerMilliLiter) {
            this.densityGramsPerMilliLiter = densityGramsPerMilliLiter;
            return this;
        }

        public SanitizerBuilder mainChemicalFamily(Ingredient.ChemicalFamily mainChemicalFamily) {
            this.mainChemicalFamily = mainChemicalFamily;
            return this;
        }

        public SanitizerBuilder requiresDilution(boolean requiresDilution) {
            this.requiresDilution = requiresDilution;
            return this;
        }

        @Override
        public Sanitizer build() {
            if (composition == null || composition.isEmpty()) {
                productNotification.addError("active ingredients", "active ingredients list mustn't be null/empty");
            }
            else {
                double sum = 0;
                for (Ingredient ingredient : composition) {
                    sum += ingredient.getConcentrationPercentual();
                }

                if (sum > 100) {
                    productNotification.addError("active ingredients", "total concentration of all active ingredients cannot be greater than 100%");
                }
            }

            ErrorAggregator.verifyNull(physicalForm, "form", productNotification);

            ErrorAggregator.verifyNull(mainChemicalFamily, "main chemical family", productNotification);

            StringUtils.validateString(registryNumber, 8, "register number", 14, true, productNotification);

            NumberUtils.validateNumericalAttribute(phLevel, 0, "ph level", 14, productNotification);

            NumberUtils.validateNumericalAttribute(densityGramsPerMilliLiter, 0, "density (g/mL)", 23, productNotification); // 23 is (a bit greater than) the value of density of the "Osmium", the most dense substance in the world

            StringUtils.validateString(useIndications, "use indications", true, productNotification);

            if (productNotification.hasErrors()) {
                throw new InvalidProductAttributeException(productNotification.getErrors());
            }

            return new Sanitizer(this);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum PhysicalForm {
        LIQUID("LQ"),
        SOLID("SO"),
        POWDER("PD"),
        GEL("GL"),
        SPRAY("SP"),
        FOAM("FM"),
        WIPES("WP");

        private final String code;
    }
}
