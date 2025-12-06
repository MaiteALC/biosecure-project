package br.com.biosecure.utils;

import java.time.LocalDate;
import br.com.biosecure.model.product.PersonalProtectiveEquipment;
import br.com.biosecure.model.product.PersonalProtectiveEquipment.Size;
import br.com.biosecure.model.product.Product.PackagingType;
 
public class PersonalProtectiveEquipmentBuilder {
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

    public PersonalProtectiveEquipmentBuilder withSize(Size size) {
        this.size = size;

        return this;
    }

    private static class PersonalProtectiveEquipmentDummy extends PersonalProtectiveEquipment {
        public PersonalProtectiveEquipmentDummy(String name, double price, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, int quantityPerPackage, Size size, String certificateOfApproval, boolean isDisposable) {

            super(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, isDisposable);
        }
    }

    public PersonalProtectiveEquipmentBuilder withCertificateOfApproval(String certificateOfApproval) {
        this.certificateOfApproval = certificateOfApproval;

        return this;
    }

    public PersonalProtectiveEquipmentBuilder withDisposable(boolean isDisposable) {
        this.isDisposable = isDisposable;

        return this;
    }
    
    public static PersonalProtectiveEquipmentBuilder aPPE() {
        return new PersonalProtectiveEquipmentBuilder();
    }

    public PersonalProtectiveEquipment build() {
        return new PersonalProtectiveEquipmentDummy(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, isDisposable);
    }
}
