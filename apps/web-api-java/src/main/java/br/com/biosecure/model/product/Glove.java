package br.com.biosecure.model.product;

import java.time.LocalDate;
import br.com.biosecure.utils.NotificationContext;
import br.com.biosecure.utils.NumberUtils;

public class Glove extends PPE {
    private final boolean powderFree;
    private final boolean longBarrel;
    private final GloveMaterial material;
    private final boolean isTextured;
    private final double thicknessMils;

    public Glove(String name, double price, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, int quantityPerPackage, Size size, String certificateOfApproval, boolean isDisposable, boolean isPowderFree, boolean hasLongBarrel, GloveMaterial material, boolean isTextured, double thicknessMils) {
        
        super(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, material == GloveMaterial.LATEX || material == GloveMaterial.VINYL ? true : isDisposable);

        NotificationContext notification = new NotificationContext();

        NumberUtils.validateNumericalAttribute(thicknessMils, 3, "thickness (mils)", 10, notification);

        if (notification.hasErrors()) {
            throw new InvalidProductAttributeException(notification.getErrors());
        }

        this.powderFree = isPowderFree;
        this.longBarrel = hasLongBarrel;
        this.material = material;
        this.isTextured = isTextured;
        this.thicknessMils = thicknessMils;
    }

    public enum GloveMaterial {
        LATEX,
        NITRILE, // The gold standard for labs
        VINYL,
        NEOPRENE;
    }

    public boolean isPowderFree() {
        return powderFree;
    }

    public boolean hasLongBarrel() {
        return longBarrel;
    }

    public GloveMaterial getMaterial() {
        return material;
    }

    public boolean isTextured() {
        return isTextured;
    }

    public double getThicknessMils() {
        return thicknessMils;
    }
}
