package br.com.biosecure.utils;

import java.time.LocalDate;
import br.com.biosecure.model.product.Product.PackagingType;
import br.com.biosecure.model.product.SampleContainer;
import br.com.biosecure.model.product.SampleContainer.*;

public class SampleContainerBuilder {
    // General attributes of products
    private String name = "Sanitizer Test";
    private double price = 54.90;
    private String manufacturer = "Test Manufacturer";
    private String batchNumber = "Batch-1A";
    private LocalDate expirationDate = LocalDate.of(2027, 3, 25);
    private PackagingType packagingType = PackagingType.BOX;
    private int quantityPerPackage = 30;

    // Specific attributes of Sample Container
    private ClosingMethod closingMethod = ClosingMethod.CELLULOSE_STOPPER;
    private SterilizationMethod sterilizationMethod = SterilizationMethod.ETHYLENE_OXIDE;
    private Material material = Material.BOROSILICATE_GLASS;
    private double capacity = 1;

    private static class SampleContainerDummy extends SampleContainer {
        public SampleContainerDummy(String name, double price, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, int quantityPerPackage, SterilizationMethod sterilizationMethod, ClosingMethod closingMethod, Material materialType, double capacityML) {
        
            super(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, sterilizationMethod, closingMethod, materialType, capacityML);
        }
    }

    public SampleContainerBuilder withClosingMethod(ClosingMethod closingMethod) {
        this.closingMethod = closingMethod;

        return this;
    }

    public SampleContainerBuilder withSterilizationMethod(SterilizationMethod sterilizationMethod) {
        this.sterilizationMethod = sterilizationMethod;

        return this;
    }

    public SampleContainerBuilder withMaterial(Material material) {
        this.material = material;

        return this;
    }

    public SampleContainerBuilder withCapacity(double capacity) {
        this.capacity = capacity;

        return this;
    }

    public static SampleContainerBuilder aSampleContainer() {
        return new SampleContainerBuilder();
    }
    
    public SampleContainer build() {
        return new SampleContainerDummy(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, sterilizationMethod, closingMethod, material, capacity);
    }
}
