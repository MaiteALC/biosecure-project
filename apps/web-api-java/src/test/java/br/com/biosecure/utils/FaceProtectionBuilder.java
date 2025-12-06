package br.com.biosecure.utils;

import java.time.LocalDate;
import br.com.biosecure.model.product.FaceProtection;
import br.com.biosecure.model.product.FaceProtection.ProtectionType;
import br.com.biosecure.model.product.PersonalProtectiveEquipment.Size;
import br.com.biosecure.model.product.Product.PackagingType;

public class FaceProtectionBuilder {
    // General attributes of products
    private String name = "Sanitizer Test";
    private double price = 54.90;
    private String manufacturer = "Test Manufacturer";
    private String batchNumber = "Batch-1A";
    private LocalDate expirationDate = LocalDate.of(2027, 3, 25);
    private PackagingType packagingType = PackagingType.BOX;
    private int quantityPerPackage = 20;

    // Specific attributes of Personal Protective Equipment (PPE)
    private Size size = Size.UNIVERSAL;
    private String certificateOfApproval = "CA-54967";
    private boolean isDisposable = false;

    // Specific attributes of Face Protection
    private ProtectionType type = ProtectionType.MASK_RESPIRATOR;
    private String standardRating = "N95";
    private boolean isAntiFog = false;

    public FaceProtectionBuilder withSize(Size size) {
        this.size = size;

        return this;
    }
    
    public FaceProtectionBuilder withCertificateOfApproval(String certificateOfApproval) {
        this.certificateOfApproval = certificateOfApproval;

        return this;
    }
    
    public FaceProtectionBuilder withDisposable(boolean isDisposable) {
        this.isDisposable = isDisposable;

        return this;
    }
    
    public FaceProtectionBuilder withType(ProtectionType type) {
        this.type = type;

        return this;
    }
    
    public FaceProtectionBuilder withStandardRating(String standardRating) {
        this.standardRating = standardRating;

        return this;
    }
    
    public FaceProtectionBuilder withAntiFog(boolean isAntiFog) {
        this.isAntiFog = isAntiFog;

        return this;
    }
    
    public static FaceProtectionBuilder aFaceProtection() {
        return new FaceProtectionBuilder();
    }

    public FaceProtection build() {
        return new FaceProtection(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, isDisposable, type, standardRating, isAntiFog);
    }
}
