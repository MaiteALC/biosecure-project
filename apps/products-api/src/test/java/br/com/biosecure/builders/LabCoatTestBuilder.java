package br.com.biosecure.builders;

import br.com.biosecure.model.LabCoat;
import br.com.biosecure.model.LabCoat.*;

public class LabCoatTestBuilder extends BasePpeTestBuilder<LabCoatTestBuilder, LabCoat> {
    // Specific attributes of Lab Coat
    private FabricType fabricType = FabricType.COTTON_100;
    private int grammage = 40;
    private CuffStyle cuffStyle = CuffStyle.KNITTED_CUFF;
    private CollarType collarType = CollarType.HIGH_NECK;

    public LabCoatTestBuilder withFabricType(FabricType fabricType) {
        this.fabricType = fabricType;
        return this;
    }

    public LabCoatTestBuilder withGrammage(int grammage) {
        this.grammage = grammage;
        return this;
    }

    public LabCoatTestBuilder withCuffStyle(CuffStyle cuffStyle) {
        this.cuffStyle = cuffStyle;
        return this;
    }

    public LabCoatTestBuilder withCollarType(CollarType collarType) {
        this.collarType = collarType;
        return this;
    }

    @Override
    protected LabCoatTestBuilder self() {
        return this;
    }

    public static LabCoatTestBuilder aLabCoat() {
        return new LabCoatTestBuilder();
    }

    @Override
    public LabCoat build() {
        return LabCoat.builder()
                .fabricType(fabricType)
                .grammage(grammage)
                .cuffStyle(cuffStyle)
                .collarType(collarType)
                .size(size)
                .certificateOfApproval(certificateOfApproval)
                .disposable(isDisposable)
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
