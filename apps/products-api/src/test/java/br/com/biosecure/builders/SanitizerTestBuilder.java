package br.com.biosecure.builders;

import br.com.biosecure.model.Ingredient;
import br.com.biosecure.model.Ingredient.ChemicalFamily;
import br.com.biosecure.model.Sanitizer.*;
import br.com.biosecure.model.Sanitizer;
import java.util.List;

public class SanitizerTestBuilder extends BaseProductTestBuilder<SanitizerTestBuilder, Sanitizer> {
    // Specifics attributes of Sanitizer
    private List<Ingredient> ingredients = List.of(
            new Ingredient("Alkyl Dimethyl Benzyl Ammonium Chloride", "68424-85-1", ChemicalFamily.QUATERNARY_AMMONIUM, 50, Ingredient.IngredientType.ACTIVE_INGREDIENT),
            new Ingredient("Didecyl Dimethyl Ammonium Chloride", "7173-51-5", ChemicalFamily.QUATERNARY_AMMONIUM, 50, Ingredient.IngredientType.ACTIVE_INGREDIENT)
    );
    private PhysicalForm form = PhysicalForm.LIQUID;
    private String registryNumber = "123-Test-Anv";
    private String useIndications = "Test test test test test";
    private double phLevel = 7;
    private boolean flammable = false;
    private boolean requiresDilution = false;
    private double density = 1;
    private ChemicalFamily mainChemicalFamily = ChemicalFamily.QUATERNARY_AMMONIUM;

    public SanitizerTestBuilder withActiveIngredient(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public SanitizerTestBuilder withForm(PhysicalForm form) {
        this.form = form;
        return this;
    }

    public SanitizerTestBuilder withPhLevel(double phLevel) {
        this.phLevel = phLevel;
        return this;
    }

    public SanitizerTestBuilder withFlammable(boolean flammable) {
        this.flammable = flammable;
        return this;
    }

    public SanitizerTestBuilder withRequiresDilution(boolean requiresDilution) {
        this.requiresDilution = requiresDilution;
        return this;
    }

    public SanitizerTestBuilder withDensity(double density) {
        this.density = density;
        return this;
    }

    public SanitizerTestBuilder withRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
        return this;
    }

    public SanitizerTestBuilder withUseIndications(String useIndications) {
        this.useIndications = useIndications;
        return this;
    }

    public SanitizerTestBuilder withMainChemicalFamily(ChemicalFamily mainChemicalFamily) {
        this.mainChemicalFamily = mainChemicalFamily;
        return this;
    }

    public static SanitizerTestBuilder aSanitizer() {
        return new SanitizerTestBuilder();
    }

    @Override
    protected SanitizerTestBuilder self() {
        return this;
    }

    @Override
    public Sanitizer build() {
        return Sanitizer.builder()
                .composition(ingredients)
                .physicalForm(form)
                .registryNumber(registryNumber)
                .useIndications(useIndications)
                .phLevel(phLevel)
                .requiresDilution(requiresDilution)
                .densityGramsPerMilliLiter(density)
                .flammable(flammable)
                .mainChemicalFamily(mainChemicalFamily)
                .name(name)
                .price(price)
                .manufacturer(manufacturer)
                .batchNumber(batchNumber)
                .expirationDate(expirationDate)
                .packagingType(packagingType)
                .measureUnit(measureUnit)
                .quantityPerPackage(quantityPerPackage)
                .build();
    }
}
