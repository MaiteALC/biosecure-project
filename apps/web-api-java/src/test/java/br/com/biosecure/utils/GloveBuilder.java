package br.com.biosecure.utils;

import java.time.LocalDate;
import br.com.biosecure.model.product.Glove;
import br.com.biosecure.model.product.Glove.GloveMaterial;
import br.com.biosecure.model.product.Product.PackagingType;
import br.com.biosecure.model.product.PersonalProtectiveEquipment.Size;

public class GloveBuilder {
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

    // Specific attributes of Glove
    private boolean powderFree = true;
    private boolean longBarrel = true;
    private GloveMaterial material = GloveMaterial.NITRILE;
    private boolean isTextured = false;
    private double thicknessMils = 1.5;
    
    public GloveBuilder withSize(Size size) {
        this.size = size;

        return this;
    }

    public GloveBuilder withCertificateOfApproval(String certificateOfApproval) {
        this.certificateOfApproval = certificateOfApproval;

        return this;
    }

    public GloveBuilder withDisposable(boolean isDisposable) {
        this.isDisposable = isDisposable;

        return this;
    }

    public GloveBuilder withPowderFree(boolean powderFree) {
        this.powderFree = powderFree;

        return this;
    }

    public GloveBuilder withLongBarrel(boolean longBarrel) {
        this.longBarrel = longBarrel;

        return this;
    }

    public GloveBuilder withMaterial(GloveMaterial material) {
        this.material = material;

        return this;
    }

    public GloveBuilder withTextured(boolean isTextured) {
        this.isTextured = isTextured;

        return this;
    }

    public GloveBuilder withThicknessMils(double thicknessMils) {
        this.thicknessMils = thicknessMils;

        return this;
    }

    public static GloveBuilder aGlove() {
        return new GloveBuilder();
    }

    public Glove build() {
        return new Glove(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, isDisposable, powderFree, longBarrel, material, isTextured, thicknessMils);
    }
}
