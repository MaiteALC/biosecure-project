package br.com.biosecure.utils;

import java.time.LocalDate;
import br.com.biosecure.model.product.LabCoat;
import br.com.biosecure.model.product.LabCoat.*;
import br.com.biosecure.model.product.PersonalProtectiveEquipment.Size;
import br.com.biosecure.model.product.Product.PackagingType;

public class LabCoatBuilder {
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

    // Specific attributes of Lab Coat
    private FabricType fabricType = FabricType.COTTON_100;
    private int grammage = 40;
    private CuffStyle cuffStyle = CuffStyle.KNITTED_CUFF;
    private CollarType collarType = CollarType.HIGH_NECK;
    
    public LabCoatBuilder withSize(Size size) {
        this.size = size;

        return this;
    }

    public LabCoatBuilder withCertificateOfApproval(String certificateOfApproval) {
        this.certificateOfApproval = certificateOfApproval;

        return this;
    }

    public LabCoatBuilder withFabricType(FabricType fabricType) {
        this.fabricType = fabricType;

        return this;
    }

    public LabCoatBuilder withGrammage(int grammage) {
        this.grammage = grammage;

        return this;
    }

    public LabCoatBuilder withCuffStyle(CuffStyle cuffStyle) {
        this.cuffStyle = cuffStyle;

        return this;
    }

    public LabCoatBuilder withCollarType(CollarType collarType) {
        this.collarType = collarType;

        return this;
    }

    public static LabCoatBuilder aLabCoat() {
        return new LabCoatBuilder();
    }

    public LabCoat build() {
        return new LabCoat(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, fabricType, grammage, cuffStyle, collarType);
    }
}
