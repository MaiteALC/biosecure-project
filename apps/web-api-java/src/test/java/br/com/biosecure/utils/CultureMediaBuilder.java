package br.com.biosecure.utils;

import java.time.LocalDate;
import java.util.OptionalDouble;

import br.com.biosecure.model.product.CultureMedia;
import br.com.biosecure.model.product.CultureMedia.*;
import br.com.biosecure.model.product.Product.MeasureUnit;
import br.com.biosecure.model.product.Product.PackagingType;

public class CultureMediaBuilder {
    // General attributes of products
    private String name = "Sanitizer Test";
    private double price = 54.90;
    private String manufacturer = "Test Manufacturer";
    private String batchNumber = "Batch-1A";
    private LocalDate expirationDate = LocalDate.of(2027, 7, 30);
    private PackagingType packagingType = PackagingType.BOTTLE;
    private MeasureUnit measureUnit = MeasureUnit.L;
    private double quantityPerPackage = 15.0;

    // Specific attributes of Culture Media
    private CultureMediaFinality finality = CultureMediaFinality.SELECTIVE;
    private PhysicalUnit physicalUnit = PhysicalUnit.PREPARED_LIQUID_PLATE;
    private boolean protectOfLight = true;
    private StorageConditions storageConditions = StorageConditions.FROZEN;
    private OptionalDouble preparationGramsPerLiter = OptionalDouble.of(2);
    private double finalPhLevel = 7;
    private double quantityPerUnit = 5;
    private QuantificationUnit quantificationUnit = QuantificationUnit.ML;

    public static CultureMediaBuilder aCultureMediaBuilder() {
        return new CultureMediaBuilder();
    }
    
    public CultureMediaBuilder withFinality(CultureMediaFinality finality) {
        this.finality = finality;

        return this;
    }

    public CultureMediaBuilder withStorageConditions(StorageConditions storageConditions) {
        this.storageConditions = storageConditions;

        return this;
    }

    public CultureMediaBuilder withPhysicalUnit(PhysicalUnit physicalUnit) {
        this.physicalUnit = physicalUnit;

        return this;
    }

    public CultureMediaBuilder withProtectOfLight(boolean protectOfLight) {
        this.protectOfLight = protectOfLight;

        return this;
    }

    public CultureMediaBuilder withPreparationGramsPerLiter(double preparationGramsPerLiter) {
        this.preparationGramsPerLiter = OptionalDouble.of(preparationGramsPerLiter);

        return this;
    }

    public CultureMediaBuilder withFinalPhLevel(double finalPhLevel) {
        this.finalPhLevel = finalPhLevel;

        return this;
    }

    public CultureMediaBuilder withQuantityPerUnit(double quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;

        return this;
    }

    public CultureMediaBuilder withQuantificationUnit(QuantificationUnit quantificationUnit) {
        this.quantificationUnit = quantificationUnit;

        return this;
    }

    public CultureMedia build() {
        return new CultureMedia(name, price, physicalUnit, manufacturer, batchNumber, expirationDate, packagingType,measureUnit, quantityPerPackage, finality, storageConditions, protectOfLight, quantityPerUnit, quantificationUnit, preparationGramsPerLiter.getAsDouble(), finalPhLevel);
    }
}
