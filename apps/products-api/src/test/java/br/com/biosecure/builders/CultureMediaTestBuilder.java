package br.com.biosecure.builders;

import br.com.biosecure.model.CultureMedia;
import br.com.biosecure.model.CultureMedia.CultureMediaFinality;
import br.com.biosecure.model.CultureMedia.Presentation;
import br.com.biosecure.model.CultureMedia.QuantificationUnit;
import br.com.biosecure.model.CultureMedia.StorageConditions;

import java.util.OptionalDouble;

public class CultureMediaTestBuilder extends BaseProductTestBuilder<CultureMediaTestBuilder, CultureMedia> {
    // Specific attributes of Culture Media
    private CultureMediaFinality finality = CultureMediaFinality.SELECTIVE;
    private Presentation presentation = Presentation.PREPARED_LIQUID_PLATE;
    private boolean protectOfLight = true;
    private StorageConditions storageConditions = StorageConditions.FROZEN;
    private OptionalDouble preparation = OptionalDouble.of(2);
    private double finalPhLevel = 7;
    private double quantityPerUnit = 5;
    private QuantificationUnit quantificationUnit = QuantificationUnit.ML;

    public static CultureMediaTestBuilder aCultureMediaBuilder() {
        return new CultureMediaTestBuilder();
    }
    
    public CultureMediaTestBuilder withFinality(CultureMediaFinality finality) {
        this.finality = finality;
        return this;
    }

    public CultureMediaTestBuilder withStorageConditions(StorageConditions storageConditions) {
        this.storageConditions = storageConditions;
        return this;
    }

    public CultureMediaTestBuilder withPresentationForm(Presentation form) {
        this.presentation = form;
        return this;
    }

    public CultureMediaTestBuilder withProtectOfLight(boolean protectOfLight) {
        this.protectOfLight = protectOfLight;
        return this;
    }

    public CultureMediaTestBuilder withPreparationGramsPerLiter(OptionalDouble preparation) {
        this.preparation = preparation == null ? OptionalDouble.empty() : preparation;
        return this;
    }

    public CultureMediaTestBuilder withFinalPhLevel(double finalPhLevel) {
        this.finalPhLevel = finalPhLevel;
        return this;
    }

    public CultureMediaTestBuilder withQuantityPerUnit(double quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
        return this;
    }

    public CultureMediaTestBuilder withQuantificationUnit(QuantificationUnit quantificationUnit) {
        this.quantificationUnit = quantificationUnit;
        return this;
    }

    @Override
    protected CultureMediaTestBuilder self() {
        return this;
    }

    @Override
    public CultureMedia build() {
        return CultureMedia.builder()
                .cultureMediaFinality(finality)
                .storageConditions(storageConditions)
                .presentation(presentation)
                .protectOfLight(protectOfLight)
                .preparationGramsPerLiter(preparation)
                .finalPhLevel(finalPhLevel)
                .quantityPerUnit(quantityPerUnit)
                .quantificationUnit(quantificationUnit)
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
