package br.com.biosecure.builders;

import br.com.biosecure.model.FaceProtection;
import br.com.biosecure.model.FaceProtection.ProtectionType;

public class FaceProtectionTestBuilder extends BasePpeTestBuilder<FaceProtectionTestBuilder, FaceProtection> {
    // Specific attributes of Face Protection
    private ProtectionType type = ProtectionType.MASK_RESPIRATOR;
    private String standardRating = "N95";
    private boolean isAntiFog = false;
    private boolean hasValve = false;

    public FaceProtectionTestBuilder withType(ProtectionType type) {
        this.type = type;
        return this;
    }
    
    public FaceProtectionTestBuilder withStandardRating(String standardRating) {
        this.standardRating = standardRating;
        return this;
    }
    
    public FaceProtectionTestBuilder withAntiFog(boolean isAntiFog) {
        this.isAntiFog = isAntiFog;
        return this;
    }

    public FaceProtectionTestBuilder withValve(boolean hasValve) {
        this.hasValve = hasValve;
        return this;
    }
    
    public static FaceProtectionTestBuilder aFaceProtection() {
        return new FaceProtectionTestBuilder();
    }

    @Override
    protected FaceProtectionTestBuilder self() {
        return this;
    }

    @Override
    public FaceProtection build() {
        return FaceProtection.builder()
                .protectionType(type)
                .standardRating(standardRating)
                .hasValve(hasValve)
                .antiFog(isAntiFog)
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
