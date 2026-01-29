package br.com.biosecure.builders;

import br.com.biosecure.model.SampleBag.*;
import br.com.biosecure.model.SampleBag;
import br.com.biosecure.model.SampleContainer.Material;

public class SampleBagTestBuilder extends BaseSampleContainerTestBuilder<SampleBagTestBuilder, SampleBag> {
    { material = Material.PE; }
    
    // Specific attributes of Sample Bag
    private FilterType filter = FilterType.FULL_PAGE;
    private boolean identificationTag = true;
    private boolean standUp = false;
    private double thicknessMm = 3;
    private double widthMm = 10;
    private double heightMm = 22;
    private double capacityMilliLiters = 2;

    public SampleBagTestBuilder withFilter(FilterType filter) {
        this.filter = filter;
        return this;
    }

    public SampleBagTestBuilder withIdentificationTag(boolean identificationTag) {
        this.identificationTag = identificationTag;
        return this;
    }

    public SampleBagTestBuilder withStandUp(boolean standUp) {
        this.standUp = standUp;
        return this;
    }

    public SampleBagTestBuilder withThicknessMm(double thicknessMm) {
        this.thicknessMm = thicknessMm;
        return this;
    }

    public SampleBagTestBuilder withWidthMm(double widthMm) {
        this.widthMm = widthMm;
        return this;
    }

    public SampleBagTestBuilder withHeightMm(double heightMm) {
        this.heightMm = heightMm;
        return this;
    }

    public SampleBagTestBuilder withCapacityMilliLiters(double capacity) {
        this.capacityMilliLiters = capacity;
        return this;
    }

    @Override
    protected SampleBagTestBuilder self() {
        return this;
    }

    public static SampleBagTestBuilder aSampleBag() {
        return new SampleBagTestBuilder();
    }

    @Override
    public SampleBag build() {
        return SampleBag.builder()
                .filter(filter)
                .identificationTag(identificationTag)
                .standUp(standUp)
                .thicknessMm(thicknessMm)
                .widthMm(widthMm)
                .heightMm(heightMm)
                .capacityMilliLiters(capacityMilliLiters)
                .materialType(material)
                .closingMethod(closingMethod)
                .sterilizationMethod(sterilizationMethod)
                .name(name)
                .price(price)
                .manufacturer(manufacturer)
                .batchNumber(batchNumber)
                .expirationDate(expirationDate)
                .packagingType(packagingType)
                .quantityPerPackage(quantityPerPackage)
                .build();
    }
}
